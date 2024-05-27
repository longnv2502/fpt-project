using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Models
{
    public partial class Customer
    {
        public string GenderString
        {
            get { return Gender == true ? "Male" : "Female"; }
        }
    }
}
