using System;
using System.Collections.Generic;

namespace ElectronicMedia.Models
{
    public partial class User
    {
        public User()
        {
            Comments = new HashSet<Comment>();
            Interactions = new HashSet<Interaction>();
            Views = new HashSet<View>();
        }

        public long UserId { get; set; }
        public string Username { get; set; } = null!;
        public string Email { get; set; } = null!;
        public string? Fullname { get; set; }
        public string? Image { get; set; }
        public bool? Gender { get; set; }
        public DateTime? Dob { get; set; }
        public string? Phone { get; set; }
        public string? Address { get; set; }
        public DateTime CreateTime { get; set; }
        public DateTime UpdateTime { get; set; }
        public string Role { get; set; } = null!;
        public bool? Status { get; set; }
        public byte[] PasswordHash { get; set; } = null!;
        public byte[] PasswordSalt { get; set; } = null!;
        public string RefreshToken { get; set; } = null!;
        public DateTime TokenCreated { get; set; }
        public DateTime TokenExpires { get; set; }

        public virtual ICollection<Comment> Comments { get; set; }
        public virtual ICollection<Interaction> Interactions { get; set; }
        public virtual ICollection<View> Views { get; set; }
    }
}
