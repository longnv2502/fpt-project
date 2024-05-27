using System;
using System.Collections.Generic;

namespace DataAccess.Models
{
    public partial class Type
    {
        public Type()
        {
            Rooms = new HashSet<Room>();
        }

        public int Id { get; set; }
        public string Name { get; set; } = null!;
        public decimal UnitPrice { get; set; }

        public virtual ICollection<Room> Rooms { get; set; }
    }
}
