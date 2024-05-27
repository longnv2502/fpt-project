using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Models
{
    public partial class Contract
    {
        [NotMapped]
        public bool? IsSelected { get; set; } = false;

        public string? CustomerName
        {
            get { return Customer?.Name ?? null; }
        }

        public string? RoomName
        {
            get { return Room?.Name ?? null; }
        }

        public decimal? RoomPrice
        {
            get { return Room?.Type?.UnitPrice; }
        }

        public string? RoomType
        {
            get { return Room?.Type?.Name ?? null; }
        }
    }
}
