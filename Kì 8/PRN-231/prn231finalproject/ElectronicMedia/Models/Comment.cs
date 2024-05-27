using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace ElectronicMedia.Models
{
    public partial class Comment
    {
        public long CommentId { get; set; }
        public long FkArticleId { get; set; }
        public long FkUserId { get; set; }
        public long? ParentCommentId { get; set; }
        public string CommentContent { get; set; } = null!;
        public DateTime? CreateTime { get; set; }
        public DateTime? UpdateTime { get; set; }
        public int? LikeNumber { get; set; }
        public bool? Report { get; set; }
        public virtual Comment ParentComment { get; set; } = null;
        public virtual Article FkArticle { get; set; } = null!;
        public virtual User FkUser { get; set; } = null!;
        public ICollection<Comment>? ChildrenComments { get; set; }
    }
}
