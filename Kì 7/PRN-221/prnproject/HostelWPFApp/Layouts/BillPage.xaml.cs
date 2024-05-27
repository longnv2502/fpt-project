using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Diagnostics.Metrics;
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
using DataAccess.Models;
using HostelWPFApp.ViewModels;

namespace HostelWPFApp.Layout
{
    /// <summary>
    /// Interaction logic for Contract.xaml
    /// </summary>
    public partial class BillPage : UserControl
    {
        public BillPage()
        {
            InitializeComponent();
            this.DataContext = new BillPageViewModel();


        }
    }


}
