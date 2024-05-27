using System;
using System.Collections.Generic;

namespace ElectronicMedia.Models
{
    public partial class ArticlesAdvertisement
    {
        public long FkArticleId { get; set; }
        public long FkAdvertisementId { get; set; }

        public virtual Avertisement FkAdvertisement { get; set; } = null!;
        public virtual Article FkArticle { get; set; } = null!;
    }
}
