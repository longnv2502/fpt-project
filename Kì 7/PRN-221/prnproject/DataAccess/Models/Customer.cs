using System;
using System.Collections.Generic;

namespace DataAccess.Models
{
    public partial class Customer
    {
        public Customer()
        {
            Contracts = new HashSet<Contract>();
        }

        public int Id { get; set; }
        public string Name { get; set; } = null!;
        public string Phone { get; set; } = null!;
        public DateTime Birthdate { get; set; }
        public string CardId { get; set; } = null!;
        public string Role { get; set; } = null!;
        public bool Gender { get; set; }
        public string? Avatar { get; set; }
        public string Username { get; set; } = null!;
        public string Password { get; set; } = null!;
        public string Address { get; set; } = null!;

        public virtual ICollection<Contract> Contracts { get; set; }
    }
}
