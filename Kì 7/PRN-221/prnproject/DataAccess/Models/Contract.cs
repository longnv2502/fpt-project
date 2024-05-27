using System;
using System.Collections.Generic;

namespace DataAccess.Models
{
    public partial class Contract
    {
        public Contract()
        {
            Bills = new HashSet<Bill>();
        }

        public int Id { get; set; }
        public int CustomerId { get; set; }
        public int RoomId { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime? EndDate { get; set; }
        public bool Status { get; set; }

        public virtual Customer Customer { get; set; } = null!;
        public virtual Room Room { get; set; } = null!;
        public virtual ICollection<Bill> Bills { get; set; }
    }
}
