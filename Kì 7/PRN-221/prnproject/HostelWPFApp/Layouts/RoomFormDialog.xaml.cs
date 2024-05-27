using HostelWPFApp.ViewModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace HostelWPFApp.Layout
{
    /// <summary>
    /// Interaction logic for RoomForm.xaml
    /// </summary>
    public partial class RoomFormDialog : UserControl
    {
        public RoomFormDialog()
        {
            InitializeComponent();
            this.DataContext = new RoomFormDialogViewModel();
        }

        public RoomFormDialog(int id)
        {
            InitializeComponent();
            this.DataContext = new RoomFormDialogViewModel(id);
        }
    }
}
