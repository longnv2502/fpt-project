using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.ExtensionModels
{
    public class Status
    {
        public string Name { get; set; }
        public bool Value { get; set; }

        public Status() { }
        public Status(string name, bool value)
        {
            Name = name;
            Value = value;
        }
    }
}
