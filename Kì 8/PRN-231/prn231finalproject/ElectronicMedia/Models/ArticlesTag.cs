using System;
using System.Collections.Generic;

namespace ElectronicMedia.Models
{
    public partial class ArticlesTag
    {
        public long FkArticleId { get; set; }
        public long FkTagId { get; set; }

        public virtual Article FkArticle { get; set; } = null!;
        public virtual Tag FkTag { get; set; } = null!;
    }
}
