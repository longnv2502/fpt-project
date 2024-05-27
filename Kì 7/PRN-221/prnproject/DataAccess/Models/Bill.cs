using System;
using System.Collections.Generic;

namespace DataAccess.Models
{
    public partial class Bill
    {
        public Bill()
        {
            BillServices = new HashSet<BillService>();
        }

        public int Id { get; set; }
        public int ContractId { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime? EndDate { get; set; }
        public decimal? TotalPrice { get; set; }
        public bool Status { get; set; }

        public virtual Contract Contract { get; set; } = null!;
        public virtual ICollection<BillService> BillServices { get; set; }
    }
}
