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
    internal class CustomerFormDialogViewModel
    {
        #region Property
        private HostelManagerContext _context;
        public Customer Customer { get; set; }
        public IEnumerable GenderList { get; set; }
        public IEnumerable RoleList { get; set; }
        public RelayCommand SaveCommand { get; set; }
        public Gender Gender { get; set; }
        #endregion

        #region Constructor
        public CustomerFormDialogViewModel()
        {
            this._context = HostelManagerContext.Instance;
            LoadData();
            Customer = new Customer();
            Customer.Birthdate = DateTime.Now;
            Gender = new Gender();

        }
        public CustomerFormDialogViewModel(int id)
        {
            this._context = new HostelManagerContext();
            LoadData();
            Customer = _context.Customers.Where(c0 => c0.Id == id).First();
            Gender = GetGender(Customer.Gender);
        }
        #endregion

        #region Constructor
        private void LoadData()
        {
            SaveCommand = new RelayCommand(Save);
            GenderList = new List<Gender>()
            {
                new Gender("Male", true),
                new Gender("Female", false)
            };
            RoleList = new List<string>() { "customer", "adminstrator"};

        }

        private void Save()
        {
            try
            {
                Customer.Gender = Gender.Value;
                _ = Customer.Id == null ? _context.Customers.Add(Customer) : _context.Customers.Update(Customer);
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

        private Gender GetGender(bool gender)
        {
            foreach (Gender item in GenderList)
            {
                if (item.Value == gender)
                {
                    return item;
                }
            }
            return null;
        }
    }
}
