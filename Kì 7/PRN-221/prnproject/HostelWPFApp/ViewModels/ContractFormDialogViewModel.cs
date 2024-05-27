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
    internal class ContractFormDialogViewModel
    {
        #region Property
        private HostelManagerContext _context;
        public IEnumerable Customers { get; set; }
        public IEnumerable Rooms { get; set; }
        public IEnumerable StatusLst { get; set; }
        public Contract Contract { get; set; }
        public Status Status { get; set; }

        public RelayCommand SaveCommand { get; set; }
        #endregion

        #region Constructor
        public ContractFormDialogViewModel()
        {
            this._context = HostelManagerContext.Instance;
            LoadData();
            Contract = new Contract();
            Contract.StartDate = DateTime.Now;
            Status = new Status();

        }

        public ContractFormDialogViewModel(int id)
        {
            this._context = new HostelManagerContext();
            LoadData();
            Contract = _context.Contracts.Include(m0 => m0.Customer).Include(m0 => m0.Room).Where(c0 => c0.Id == id).First();
            Status = GetStatus(Contract.Status);

        }
        #endregion

        #region LoadData
        private void LoadData()
        {
            SaveCommand = new RelayCommand(Save);
            Customers = _context.Customers.ToList();
            Rooms = _context.Rooms.ToList();
            StatusLst = new List<Status>()
            {
                new Status("Active", true),
                new Status("Deactive", false)
            };
        }

        private void Save()
        {
            try
            {
                Contract.CustomerId = Contract.Customer.Id;
                Contract.RoomId = Contract.Room.Id;
                Contract.Status = Status.Value;
                _ = Contract.Id == null ? _context.Contracts.Add(Contract) : _context.Contracts.Update(Contract);
                _context.SaveChanges();
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
