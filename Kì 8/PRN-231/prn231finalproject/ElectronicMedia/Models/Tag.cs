using System;
using System.Collections.Generic;

namespace ElectronicMedia.Models
{
    public partial class Tag
    {
        public long TagId { get; set; }
        public string TagName { get; set; } = null!;
        public DateTime? CreateTime { get; set; }
        public DateTime? UpdateTime { get; set; }
        public string? CreateUser { get; set; }
        public string? UpdateUser { get; set; }
        public bool? Status { get; set; }
        public string? Description { get; set; }
    }
}
