using System;
using System.Collections.Generic;

namespace DataAccess.Models
{
    public partial class Room
    {
        public Room()
        {
            Contracts = new HashSet<Contract>();
        }

        public int Id { get; set; }
        public string Name { get; set; } = null!;
        public int TypeId { get; set; }
        public bool Status { get; set; }

        public virtual Type Type { get; set; } = null!;
        public virtual ICollection<Contract> Contracts { get; set; }
    }
}
