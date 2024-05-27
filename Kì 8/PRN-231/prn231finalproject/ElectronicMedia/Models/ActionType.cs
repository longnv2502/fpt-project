using System;
using System.Collections.Generic;

namespace ElectronicMedia.Models
{
    public partial class ActionType
    {
        public ActionType()
        {
            Interactions = new HashSet<Interaction>();
        }

        public long ActionTypeId { get; set; }
        public string ActionName { get; set; } = null!;
        public DateTime? CreateTime { get; set; }
        public DateTime? UpdateTime { get; set; }
        public string? CreateUser { get; set; }
        public string? UpdateUser { get; set; }
        public bool? Status { get; set; }

        public virtual ICollection<Interaction> Interactions { get; set; }
    }
}
