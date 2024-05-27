using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Models
{
    public partial class Bill
    {
        public string? RoomName
        {
            get { return Contract?.Room?.Name ?? null; }
        }
        public string? CustomerName
        {
            get { return Contract?.Customer?.Name ?? null; }
        }

        public decimal? RoomPrice
        {
            get { return Contract?.Room?.Type?.UnitPrice; }
        }

        public decimal? ServicePrice
        {
            get { return BillServices?.Sum(x => x.Price); }
        }
    }
}
