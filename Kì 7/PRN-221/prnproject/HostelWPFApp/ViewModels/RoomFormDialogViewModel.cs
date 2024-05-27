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
    internal class RoomFormDialogViewModel
    {
        #region Property
        private HostelManagerContext _context;
        public IEnumerable Types { get; set; }
        public IEnumerable StatusLst { get; set; }
        public Room Room { get; set; }
        public Status Status { get; set; }

        public RelayCommand SaveCommand { get; set; }
        #endregion

        #region Constructor
        public RoomFormDialogViewModel()
        {
            this._context = HostelManagerContext.Instance;
            LoadData();
            Room = new Room();
            Status = new Status();

        }

        public RoomFormDialogViewModel(int id)
        {
            this._context = new HostelManagerContext();
            LoadData();
            Room = _context.Rooms.Include(m0 => m0.Type).Where(c0 => c0.Id == id).First();
            Status = GetStatus(Room.Status);

        }
        #endregion

        #region Constructor
        private void LoadData()
        {
            SaveCommand = new RelayCommand(Save);
            Types = _context.Types.ToList();
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
                Room.TypeId = Room.Type.Id;
                Room.Status = Status.Value;
                _ = Room.Id == null ? _context.Rooms.Add(Room) : _context.Rooms.Update(Room);
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
