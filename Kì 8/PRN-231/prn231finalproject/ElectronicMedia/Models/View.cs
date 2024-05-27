using System;
using System.Collections.Generic;

namespace ElectronicMedia.Models
{
    public partial class View
    {
        public long ViewId { get; set; }
        public long FkArticleId { get; set; }
        public long FkUserId { get; set; }
        public DateTime CreateTime { get; set; }

        public virtual Article FkArticle { get; set; } = null!;
        public virtual User FkUser { get; set; } = null!;
    }
}
