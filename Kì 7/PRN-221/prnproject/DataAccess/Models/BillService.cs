using System;
using System.Collections.Generic;

namespace DataAccess.Models
{
    public partial class BillService
    {
        public int BillId { get; set; }
        public int ServiceId { get; set; }
        public int Unit { get; set; }
        public decimal Price { get; set; }

        public virtual Bill Bill { get; set; } = null!;
        public virtual Service Service { get; set; } = null!;
    }
}
