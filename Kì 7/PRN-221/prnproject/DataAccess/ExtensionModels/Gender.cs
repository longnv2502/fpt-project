using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.ExtensionModels
{
    public class Gender
    {
        public string Name { get; set; }
        public bool Value { get; set; }

        public Gender() { }
        public Gender(string name, bool value)
        {
            Name = name;
            Value = value;
        }
    }
}
