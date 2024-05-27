using System;
using System.Collections.Generic;

namespace ElectronicMedia.Models
{
    public partial class Avertisement
    {
        public long AdvertisementId { get; set; }
        public string Title { get; set; } = null!;
        public string ImageUrl { get; set; } = null!;
        public string TargetUrl { get; set; } = null!;
        public DateTime? CreateTime { get; set; }
        public DateTime? UpdateTime { get; set; }
        public string? CreateUser { get; set; }
        public string? UpdateUser { get; set; }
        public bool? Status { get; set; }
        public string AdApi { get; set; } = null!;
    }
}
