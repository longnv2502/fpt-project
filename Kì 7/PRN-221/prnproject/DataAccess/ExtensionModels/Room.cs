using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Models
{
    public partial class Room
    {
        public string? RoomType
        {
            get { return Type?.Name ?? null; }
        }

        public string? CustomerName
        {
            get { return Contracts?.ToList().LastOrDefault(x => x.Status == true)?.CustomerName ?? null;}
        }
    }
}
