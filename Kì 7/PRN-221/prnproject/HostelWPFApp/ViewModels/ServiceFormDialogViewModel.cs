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
using MaterialDesignThemes.Wpf;
using System.Windows;

namespace HostelWPFApp.ViewModels
{
    internal class ServiceFormDialogViewModel
    {
        #region Property
        private HostelManagerContext _context;
        public IEnumerable StatusLst { get; set; }
        public Service Service { get; set; }
        public Status Status { get; set; }

        public RelayCommand SaveCommand { get; set; }
        #endregion

        #region Constructor
        public ServiceFormDialogViewModel()
        {
            this._context = HostelManagerContext.Instance;
            LoadData();
            Service = new Service();
            Status = new Status();

        }

        public ServiceFormDialogViewModel(int id)
        {
            this._context = new HostelManagerContext();
            LoadData();
            Service = _context.Services.Where(c0 => c0.ServiceId == id).First();
            Status = GetStatus(Service.ServiceStatus);

        }
        #endregion

        #region Constructor
        private void LoadData()
        {
            SaveCommand = new RelayCommand(Save);
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
                Service.ServiceStatus = Status.Value;
                _ = Service.ServiceId == null ? _context.Services.Add(Service) : _context.Services.Update(Service);
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
