﻿using DataAccess.Models;
using HostelWPFApp.Layout;
using MaterialDesignColors;
using MaterialDesignThemes.Wpf;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Internal;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using System.Windows;
using TriggerInvokeComand01.Commands;
using System.Runtime.ConstrainedExecution;
using DataAccess.ExtensionModels;

namespace HostelWPFApp.ViewModels
{
    class CustomerPageViewModel : INotifyPropertyChanged
    {
        #region Property
        private HostelManagerContext _context;
        public RelayCommand<string> SearchCommand { get; set; }
        public RelayCommand CreateCommand { get; set; }
        public RelayCommand<int> UpdateCommand { get; set; }
        public RelayCommand<int> DeleteCommand { get; set; }
        public bool IsAllItemsSelected { get; set; }

        private int totalItems;
        public int TotalItems
        {
            get { return totalItems; }
            set { totalItems = value; OnPropertyChanged(); }
        }

        private int currentPage;

        public int CurrentPage
        {
            get { return currentPage; }
            set { currentPage = value; OnPropertyChanged(); }
        }
        public RelayCommand PrevCommand { get; set; }
        public RelayCommand NextCommand { get; set; }
        private List<Customer> dataSource;
        private int PageSize = 10;
        #endregion

        #region Constructor
        public CustomerPageViewModel()
        {
            this._context = HostelManagerContext.Instance;
            ResertData();
            SearchCommand = new RelayCommand<string>(Search);
            CreateCommand = new RelayCommand(Create);
            PrevCommand = new RelayCommand(PrevPage);
            NextCommand = new RelayCommand(NextPage);
            UpdateCommand = new RelayCommand<int>(Update);
            DeleteCommand = new RelayCommand<int>(Delete);
        }
        #endregion

        #region INotifyPropertyChanged_Implementation
        public event PropertyChangedEventHandler? PropertyChanged;

        private void OnPropertyChanged([CallerMemberName] string propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
        #endregion

        #region LoadData
        private ObservableCollection<Customer> customerList;
        public ObservableCollection<Customer> CustomerList
        {
            get { return customerList; }
            set { customerList = value; OnPropertyChanged(); }
        }
        private void ResertData()
        {
            _context.ChangeTracker.Clear();
            CurrentPage = 1;
            dataSource = _context.Customers.ToList();
            TotalItems = dataSource.Count;
            LoadPage();
        }

        private void LoadData(List<Customer> dataSource)
        {
            CustomerList = new ObservableCollection<Customer>(dataSource);
        }
        #endregion

        #region Search
        public void Search(string textSearch) { }
        #endregion

        #region Create
        public async void Create()
        {
            var result = (MessageBoxResult)await DialogHost.Show(new CustomerFormDialog(), "RootDialog");
            if (result == MessageBoxResult.OK)
            {
                ResertData();
            }
        }
        #endregion

        #region Update
        public async void Update(int id)
        {
            var result = (MessageBoxResult)await DialogHost.Show(new CustomerFormDialog(id), "RootDialog");
            if (result == MessageBoxResult.OK)
            {
                ResertData();
            }
        }
        #endregion

        #region Delete
        public void Delete(int id)
        {
            try
            {
                Customer customer = _context.Customers.FirstOrDefault(x => x.Id == id);
                _context.Customers.Remove(customer);
                _context.SaveChanges();
                ResertData();
            }
            catch (Exception e)
            {
                MessageBox.Show(e.Message, "Error when delete");
            }

        }
        #endregion

        #region Paging
        private void NextPage()
        {
            if (CurrentPage >= Math.Ceiling((decimal)dataSource.Count / PageSize))
            {
                return;
            }
            CurrentPage += 1;
            LoadPage();
        }

        private void PrevPage()
        {
            if (CurrentPage <= 1)
            {
                return;
            }
            CurrentPage -= 1;
            LoadPage();
        }

        private void LoadPage()
        {
            LoadData(dataSource.Skip((CurrentPage - 1) * PageSize).Take(PageSize).ToList());
        }
        #endregion
    }
}
