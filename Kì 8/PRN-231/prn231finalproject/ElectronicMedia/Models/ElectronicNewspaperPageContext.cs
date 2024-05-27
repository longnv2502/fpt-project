using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace ElectronicMedia.Models
{
    public partial class ElectronicNewspaperPageContext : DbContext
    {
        public ElectronicNewspaperPageContext()
        {
        }

        public ElectronicNewspaperPageContext(DbContextOptions<ElectronicNewspaperPageContext> options)
            : base(options)
        {
        }

        public virtual DbSet<ActionType> ActionTypes { get; set; } = null!;
        public virtual DbSet<Article> Articles { get; set; } = null!;
        public virtual DbSet<ArticlesAdvertisement> ArticlesAdvertisements { get; set; } = null!;
        public virtual DbSet<ArticlesTag> ArticlesTags { get; set; } = null!;
        public virtual DbSet<Avertisement> Avertisements { get; set; } = null!;
        public virtual DbSet<Category> Categories { get; set; } = null!;
        public virtual DbSet<Comment> Comments { get; set; } = null!;
        public virtual DbSet<Config> Configs { get; set; } = null!;
        public virtual DbSet<Interaction> Interactions { get; set; } = null!;
        public virtual DbSet<Tag> Tags { get; set; } = null!;
        public virtual DbSet<User> Users { get; set; } = null!;
        public virtual DbSet<View> Views { get; set; } = null!;

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. You can avoid scaffolding the connection string by using the Name= syntax to read it from configuration - see https://go.microsoft.com/fwlink/?linkid=2131148. For more guidance on storing connection strings, see http://go.microsoft.com/fwlink/?LinkId=723263.
                optionsBuilder.UseSqlServer("Server=LONGNV\\LONGNV;uid=sa;pwd=123456;database=ElectronicNewspaperPage;Trusted_Connection=true");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<ActionType>(entity =>
            {
                entity.ToTable("Action_type");

                entity.Property(e => e.ActionTypeId).HasColumnName("action_type_id");

                entity.Property(e => e.ActionName)
                    .HasMaxLength(50)
                    .HasColumnName("action_name");

                entity.Property(e => e.CreateTime)
                    .HasColumnType("datetime")
                    .HasColumnName("create_time");

                entity.Property(e => e.CreateUser)
                    .HasMaxLength(50)
                    .HasColumnName("create_user");

                entity.Property(e => e.Status).HasColumnName("status");

                entity.Property(e => e.UpdateTime)
                    .HasColumnType("datetime")
                    .HasColumnName("update_time");

                entity.Property(e => e.UpdateUser)
                    .HasMaxLength(50)
                    .HasColumnName("update_user");
            });

            modelBuilder.Entity<Article>(entity =>
            {
                entity.Property(e => e.ArticleId).HasColumnName("article_id");

                entity.Property(e => e.Content).HasColumnName("content");

                entity.Property(e => e.CreateTime)
                    .HasColumnType("datetime")
                    .HasColumnName("create_time");

                entity.Property(e => e.CreateUser)
                    .HasMaxLength(50)
                    .HasColumnName("create_user");

                entity.Property(e => e.FkCategoryId).HasColumnName("fk_category_id");

                entity.Property(e => e.Status).HasColumnName("status");

                entity.Property(e => e.Title)
                    .HasMaxLength(255)
                    .HasColumnName("title");

                entity.Property(e => e.UpdateTime)
                    .HasColumnType("datetime")
                    .HasColumnName("update_time");

                entity.Property(e => e.UpdateUser)
                    .HasMaxLength(50)
                    .HasColumnName("update_user");

                entity.HasOne(d => d.FkCategory)
                    .WithMany(p => p.Articles)
                    .HasForeignKey(d => d.FkCategoryId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Articles_Categories");
            });

            modelBuilder.Entity<ArticlesAdvertisement>(entity =>
            {
                entity.HasNoKey();

                entity.ToTable("Articles_Advertisement");

                entity.Property(e => e.FkAdvertisementId).HasColumnName("fk_advertisement_id");

                entity.Property(e => e.FkArticleId).HasColumnName("fk_article_id");

                entity.HasOne(d => d.FkAdvertisement)
                    .WithMany()
                    .HasForeignKey(d => d.FkAdvertisementId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_tbl_articles_advertisement_tbl_advertisement");

                entity.HasOne(d => d.FkArticle)
                    .WithMany()
                    .HasForeignKey(d => d.FkArticleId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_tbl_articles_advertisement_tbl_articles");
            });

            modelBuilder.Entity<ArticlesTag>(entity =>
            {
                entity.HasNoKey();

                entity.ToTable("Articles_tags");

                entity.Property(e => e.FkArticleId).HasColumnName("fk_article_id");

                entity.Property(e => e.FkTagId).HasColumnName("fk_tag_id");

                entity.HasOne(d => d.FkArticle)
                    .WithMany()
                    .HasForeignKey(d => d.FkArticleId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_tbl_articles_tags_tbl_articles");

                entity.HasOne(d => d.FkTag)
                    .WithMany()
                    .HasForeignKey(d => d.FkTagId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_tbl_articles_tags_tbl_tags");
            });

            modelBuilder.Entity<Avertisement>(entity =>
            {
                entity.HasKey(e => e.AdvertisementId)
                    .HasName("PK_Advertisement");

                entity.ToTable("Avertisement");

                entity.Property(e => e.AdvertisementId).HasColumnName("advertisement_id");

                entity.Property(e => e.AdApi)
                    .HasMaxLength(255)
                    .IsUnicode(false)
                    .HasColumnName("ad_api");

                entity.Property(e => e.CreateTime)
                    .HasColumnType("datetime")
                    .HasColumnName("create_time");

                entity.Property(e => e.CreateUser)
                    .HasMaxLength(50)
                    .HasColumnName("create_user");

                entity.Property(e => e.ImageUrl)
                    .HasMaxLength(255)
                    .IsUnicode(false)
                    .HasColumnName("image_url");

                entity.Property(e => e.Status).HasColumnName("status");

                entity.Property(e => e.TargetUrl)
                    .HasMaxLength(255)
                    .IsUnicode(false)
                    .HasColumnName("target_url");

                entity.Property(e => e.Title)
                    .HasMaxLength(50)
                    .HasColumnName("title");

                entity.Property(e => e.UpdateTime)
                    .HasColumnType("datetime")
                    .HasColumnName("update_time");

                entity.Property(e => e.UpdateUser)
                    .HasMaxLength(50)
                    .HasColumnName("update_user");
            });

            modelBuilder.Entity<Category>(entity =>
            {
                entity.Property(e => e.CategoryId).HasColumnName("category_id");

                entity.Property(e => e.CategoryName)
                    .HasMaxLength(50)
                    .HasColumnName("category_name");

                entity.Property(e => e.CreateTime)
                    .HasColumnType("datetime")
                    .HasColumnName("create_time");

                entity.Property(e => e.CreateUser)
                    .HasMaxLength(50)
                    .HasColumnName("create_user");

                entity.Property(e => e.ParentCategoryId).HasColumnName("parent_category_id");

                entity.Property(e => e.Status).HasColumnName("status");

                entity.Property(e => e.UpdateTime)
                    .HasColumnType("datetime")
                    .HasColumnName("update_time");

                entity.Property(e => e.UpdateUser)
                    .HasMaxLength(50)
                    .HasColumnName("update_user");
            });

            modelBuilder.Entity<Comment>(entity =>
            {
                entity.Property(e => e.CommentId).HasColumnName("comment_id");

                entity.Property(e => e.CommentContent)
                    .HasMaxLength(255)
                    .HasColumnName("comment_content");

                entity.Property(e => e.CreateTime)
                    .HasColumnType("datetime")
                    .HasColumnName("create_time");

                entity.Property(e => e.FkArticleId).HasColumnName("fk_article_id");

                entity.Property(e => e.FkUserId).HasColumnName("fk_user_id");

                entity.Property(e => e.LikeNumber).HasColumnName("like_number");

                entity.Property(e => e.ParentCommentId).HasColumnName("parent_comment_id");

                entity.Property(e => e.Report).HasColumnName("report");

                entity.Property(e => e.UpdateTime)
                    .HasColumnType("datetime")
                    .HasColumnName("update_time");

                entity.HasOne(d => d.FkArticle)
                    .WithMany(p => p.Comments)
                    .HasForeignKey(d => d.FkArticleId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Comments_Articles");

                entity.HasOne(d => d.FkUser)
                    .WithMany(p => p.Comments)
                    .HasForeignKey(d => d.FkUserId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Comments_Users");
            });

            modelBuilder.Entity<Config>(entity =>
            {
                entity.Property(e => e.ConfigId).HasColumnName("config_id");

                entity.Property(e => e.ConfigName)
                    .HasMaxLength(50)
                    .HasColumnName("config_name");

                entity.Property(e => e.CreateTime)
                    .HasColumnType("datetime")
                    .HasColumnName("create_time");

                entity.Property(e => e.CreateUser)
                    .HasMaxLength(50)
                    .HasColumnName("create_user");

                entity.Property(e => e.Description)
                    .HasMaxLength(255)
                    .HasColumnName("description");

                entity.Property(e => e.Status).HasColumnName("status");

                entity.Property(e => e.UpdateTime)
                    .HasColumnType("datetime")
                    .HasColumnName("update_time");

                entity.Property(e => e.UpdateUser)
                    .HasMaxLength(50)
                    .HasColumnName("update_user");
            });

            modelBuilder.Entity<Interaction>(entity =>
            {
                entity.Property(e => e.InteractionId).HasColumnName("interaction_id");

                entity.Property(e => e.CreateTime)
                    .HasColumnType("datetime")
                    .HasColumnName("create_time");

                entity.Property(e => e.FkActionTypeId).HasColumnName("fk_action_type_id");

                entity.Property(e => e.FkArticleId).HasColumnName("fk_article_id");

                entity.Property(e => e.FkUserId).HasColumnName("fk_user_id");

                entity.Property(e => e.Rate).HasColumnName("rate");

                entity.Property(e => e.UpdateTime)
                    .HasColumnType("datetime")
                    .HasColumnName("update_time");

                entity.HasOne(d => d.FkActionType)
                    .WithMany(p => p.Interactions)
                    .HasForeignKey(d => d.FkActionTypeId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_tbl_interactions_tbl_action_type");

                entity.HasOne(d => d.FkArticle)
                    .WithMany(p => p.Interactions)
                    .HasForeignKey(d => d.FkArticleId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Interactions_Articles");

                entity.HasOne(d => d.FkUser)
                    .WithMany(p => p.Interactions)
                    .HasForeignKey(d => d.FkUserId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Interactions_Users");
            });

            modelBuilder.Entity<Tag>(entity =>
            {
                entity.Property(e => e.TagId).HasColumnName("tag_id");

                entity.Property(e => e.CreateTime)
                    .HasColumnType("datetime")
                    .HasColumnName("create_time");

                entity.Property(e => e.CreateUser)
                    .HasMaxLength(50)
                    .HasColumnName("create_user");

                entity.Property(e => e.Description)
                    .HasMaxLength(255)
                    .HasColumnName("description");

                entity.Property(e => e.Status).HasColumnName("status");

                entity.Property(e => e.TagName)
                    .HasMaxLength(50)
                    .HasColumnName("tag_name");

                entity.Property(e => e.UpdateTime)
                    .HasColumnType("datetime")
                    .HasColumnName("update_time");

                entity.Property(e => e.UpdateUser)
                    .HasMaxLength(50)
                    .HasColumnName("update_user");
            });

            modelBuilder.Entity<User>(entity =>
            {
                entity.Property(e => e.UserId).HasColumnName("user_id");

                entity.Property(e => e.Address)
                    .HasMaxLength(50)
                    .HasColumnName("address");

                entity.Property(e => e.CreateTime)
                    .HasColumnType("datetime")
                    .HasColumnName("create_time");

                entity.Property(e => e.Dob)
                    .HasColumnType("datetime")
                    .HasColumnName("dob");

                entity.Property(e => e.Email)
                    .HasMaxLength(50)
                    .IsUnicode(false)
                    .HasColumnName("email");

                entity.Property(e => e.Fullname)
                    .HasMaxLength(50)
                    .HasColumnName("fullname");

                entity.Property(e => e.Gender).HasColumnName("gender");

                entity.Property(e => e.Image)
                    .IsUnicode(false)
                    .HasColumnName("image");

                entity.Property(e => e.PasswordHash)
                    .HasColumnName("password-hash")
                    .HasDefaultValueSql("(0x)");

                entity.Property(e => e.PasswordSalt)
                    .HasColumnName("password-salt")
                    .HasDefaultValueSql("(0x)");

                entity.Property(e => e.Phone)
                    .HasMaxLength(50)
                    .IsUnicode(false)
                    .HasColumnName("phone");

                entity.Property(e => e.RefreshToken)
                    .HasColumnName("refresh-token")
                    .HasDefaultValueSql("(N'')");

                entity.Property(e => e.Role)
                    .HasMaxLength(50)
                    .IsUnicode(false)
                    .HasColumnName("role");

                entity.Property(e => e.Status).HasColumnName("status");

                entity.Property(e => e.TokenCreated)
                    .HasColumnType("datetime")
                    .HasColumnName("token-created")
                    .HasDefaultValueSql("(CONVERT([bigint],(0)))");

                entity.Property(e => e.TokenExpires)
                    .HasColumnType("datetime")
                    .HasColumnName("token-expires")
                    .HasDefaultValueSql("(CONVERT([bigint],(0)))");

                entity.Property(e => e.UpdateTime)
                    .HasColumnType("datetime")
                    .HasColumnName("update_time");

                entity.Property(e => e.Username)
                    .HasMaxLength(32)
                    .IsUnicode(false)
                    .HasColumnName("username")
                    .HasDefaultValueSql("(N'')");
            });

            modelBuilder.Entity<View>(entity =>
            {
                entity.Property(e => e.ViewId).HasColumnName("view_id");

                entity.Property(e => e.CreateTime)
                    .HasColumnType("datetime")
                    .HasColumnName("create_time");

                entity.Property(e => e.FkArticleId).HasColumnName("fk_article_id");

                entity.Property(e => e.FkUserId).HasColumnName("fk_user_id");

                entity.HasOne(d => d.FkArticle)
                    .WithMany(p => p.Views)
                    .HasForeignKey(d => d.FkArticleId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Views_Articles");

                entity.HasOne(d => d.FkUser)
                    .WithMany(p => p.Views)
                    .HasForeignKey(d => d.FkUserId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Views_Users");
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
