using System;
using System.Collections.Generic;

namespace ElectronicMedia.Models
{
    public partial class Interaction
    {
        public long InteractionId { get; set; }
        public long FkUserId { get; set; }
        public long FkArticleId { get; set; }
        public long FkActionTypeId { get; set; }
        public DateTime? CreateTime { get; set; }
        public DateTime? UpdateTime { get; set; }
        public int? Rate { get; set; }

        public virtual ActionType FkActionType { get; set; } = null!;
        public virtual Article FkArticle { get; set; } = null!;
        public virtual User FkUser { get; set; } = null!;
    }
}
