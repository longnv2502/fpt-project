using DataAccess.Models;
using MaterialDesignColors;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static Microsoft.EntityFrameworkCore.DbLoggerCategory;
using TriggerInvokeComand01.Commands;
using System.Collections;
using DataAccess.ExtensionModels;
using Microsoft.EntityFrameworkCore;
using System.Collections.ObjectModel;
using System.Windows.Documents;
using System.Windows;
using MaterialDesignThemes.Wpf;

namespace HostelWPFApp.ViewModels
{
    internal class BillFormDialogViewModel
    {
        #region Property
        private HostelManagerContext _context;
        public IEnumerable Contracts { get; set; }
        public IEnumerable Services { get; set; }
        public IEnumerable StatusLst { get; set; }
        public Bill Bill { get; set; }
        public Status Status { get; set; }

        public int UnitOfElectricity { get; set; }
        public int UnitOfWater { get; set; }
        public int UnitOfCleaning { get; set; }

        public RelayCommand SaveCommand { get; set; }
        public Service ElectricityService { get; set; }
        public Service WaterService { get; set; }
        public Service CleaningService { get; set; }
        private bool isUpdate = false;
        #endregion

        #region Constructor
        public BillFormDialogViewModel()
        {
            this._context = HostelManagerContext.Instance;
            LoadData();
            Bill = new Bill();
            Bill.StartDate = DateTime.Now;
            Status = new Status();

        }

        public BillFormDialogViewModel(int id)
        {
            this._context = new HostelManagerContext();
            isUpdate = true;
            LoadData();
            Bill = _context.Bills.Include(m0 => m0.Contract).ThenInclude(m0 => m0.Customer).Where(c0 => c0.Id == id).First();
            Status = GetStatus(Bill.Status);
            List<BillService> billServices = _context.BillServices.Where(x => x.BillId == id).ToList();
            UnitOfElectricity = billServices.Where(x => x.ServiceId == 1).First().Unit;
            UnitOfWater = billServices.Where(x => x.ServiceId == 2).First().Unit;
            UnitOfCleaning = billServices.Where(x => x.ServiceId == 3).First().Unit;

        }
        #endregion

        #region Constructor
        private void LoadData()
        {
            SaveCommand = new RelayCommand(Save);
            List<Service> services;
            services = _context.Services.ToList();
            Services = services;
            Contracts = _context.Contracts.ToList();
            StatusLst = new List<Status>()
            {
                new Status("Active", true),
                new Status("Deactive", false)
            };
            ElectricityService = services.Where(x => x.ServiceName == "Electricity").First();
            WaterService = services.Where(x => x.ServiceName == "Water").First();
            CleaningService = services.Where(x => x.ServiceName == "Cleaning").First();
        }

        private async void Save()
        {
            try
            {
                Bill.ContractId = Bill.Contract.Id;
                Bill.Status = Status.Value;
                _ = isUpdate ? _context.Bills.Update(Bill) : _context.Bills.Add(Bill);
                Bill.Id = await _context.SaveChangesAsync();
                BillService ElectricityBillService = new BillService() { BillId = Bill.Id, ServiceId = ElectricityService.ServiceId, Unit = UnitOfElectricity, Price = UnitOfElectricity * ElectricityService.UnitPrice };
                BillService WaterBillService = new BillService() { BillId = Bill.Id, ServiceId = WaterService.ServiceId, Unit = UnitOfWater, Price = UnitOfWater * WaterService.UnitPrice };
                BillService CleaningBillService = new BillService() { BillId = Bill.Id, ServiceId = CleaningService.ServiceId, Unit = UnitOfCleaning, Price = UnitOfCleaning * CleaningService.UnitPrice };
                if (isUpdate)
                {
                    _context.BillServices.Update(ElectricityBillService);
                    _context.BillServices.Update(WaterBillService);
                    _context.BillServices.Update(CleaningBillService);
                } else
                {
                    _context.BillServices.Add(ElectricityBillService);
                    _context.BillServices.Add(WaterBillService);
                    _context.BillServices.Add(CleaningBillService);
                }
                Bill.TotalPrice = ElectricityBillService.Price + WaterBillService.Price + CleaningBillService.Price;
                _context.Bills.Update(Bill);
                await _context.SaveChangesAsync();
            }
            catch (Exception e)
            {
                MessageBox.Show(e.Message, "Error when save to database");
            }
            finally
            {
                DialogHost.CloseDialogCommand.Execute(MessageBoxResult.OK, null);
            }
        }
        #endregion

        private Status GetStatus(bool status)
        {
            foreach (Status item in StatusLst)
            {
                if (item.Value == status)
                {
                    return item;
                }
            }
            return null;
        }
    }
}
