using System;
using System.Collections.Generic;

namespace ElectronicMedia.Models
{
    public partial class Article
    {
        public Article()
        {
            Comments = new HashSet<Comment>();
            Interactions = new HashSet<Interaction>();
            Views = new HashSet<View>();
        }

        public long ArticleId { get; set; }
        public string Title { get; set; } = null!;
        public string Content { get; set; } = null!;
        public DateTime? CreateTime { get; set; }
        public DateTime? UpdateTime { get; set; }
        public string? CreateUser { get; set; }
        public string? UpdateUser { get; set; }
        public long FkCategoryId { get; set; }
        public bool? Status { get; set; }

        public virtual Category FkCategory { get; set; } = null!;
        public virtual ICollection<Comment> Comments { get; set; }
        public virtual ICollection<Interaction> Interactions { get; set; }
        public virtual ICollection<View> Views { get; set; }
    }
}
