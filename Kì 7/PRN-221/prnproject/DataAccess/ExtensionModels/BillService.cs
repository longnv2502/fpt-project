using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Models
{
    public partial class BillService
    {
        public string? ServiceName
        {
            get { return Service?.ServiceName ?? null; }
        }

        public string? RoomName
        {
            get { return Bill?.Contract?.RoomName ?? null; }
        }
    }
}
