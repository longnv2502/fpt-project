using System;
using System.Collections.Generic;

namespace DataAccess.Models
{
    public partial class Service
    {
        public Service()
        {
            BillServices = new HashSet<BillService>();
        }

        public int ServiceId { get; set; }
        public string ServiceName { get; set; } = null!;
        public decimal UnitPrice { get; set; }
        public bool ServiceStatus { get; set; }

        public virtual ICollection<BillService> BillServices { get; set; }
    }
}
