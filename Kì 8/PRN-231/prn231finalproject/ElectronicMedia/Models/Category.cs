using System;
using System.Collections.Generic;

namespace ElectronicMedia.Models
{
    public partial class Category
    {
        public Category()
        {
            Articles = new HashSet<Article>();
        }

        public long CategoryId { get; set; }
        public long? ParentCategoryId { get; set; }
        public string CategoryName { get; set; } = null!;
        public DateTime? CreateTime { get; set; }
        public DateTime? UpdateTime { get; set; }
        public string? CreateUser { get; set; }
        public string? UpdateUser { get; set; }
        public bool? Status { get; set; }

        public virtual ICollection<Article> Articles { get; set; }
    }
}
