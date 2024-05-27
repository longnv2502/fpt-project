IF OBJECT_ID(N'[__EFMigrationsHistory]') IS NULL
BEGIN
    CREATE TABLE [__EFMigrationsHistory] (
        [MigrationId] nvarchar(150) NOT NULL,
        [ProductVersion] nvarchar(32) NOT NULL,
        CONSTRAINT [PK___EFMigrationsHistory] PRIMARY KEY ([MigrationId])
    );
END;
GO

BEGIN TRANSACTION;
GO

CREATE TABLE [Action_type] (
    [action_type_id] bigint NOT NULL IDENTITY,
    [action_name] nvarchar(50) NOT NULL,
    [create_time] bigint NULL,
    [update_time] bigint NULL,
    [create_user] nvarchar(50) NOT NULL,
    [update_user] nvarchar(50) NOT NULL,
    [status] bit NULL,
    CONSTRAINT [PK_Action_type] PRIMARY KEY ([action_type_id])
);
GO

CREATE TABLE [Avertisement] (
    [advertisement_id] bigint NOT NULL IDENTITY,
    [title] nvarchar(50) NOT NULL,
    [image_url] varchar(255) NOT NULL,
    [target_url] varchar(255) NOT NULL,
    [create_time] bigint NULL,
    [update_time] bigint NULL,
    [create_user] nvarchar(50) NOT NULL,
    [update_user] nvarchar(50) NOT NULL,
    [status] bit NULL,
    [ad_api] varchar(255) NOT NULL,
    CONSTRAINT [PK_Advertisement] PRIMARY KEY ([advertisement_id])
);
GO

CREATE TABLE [Categories] (
    [category_id] bigint NOT NULL IDENTITY,
    [parent_category_id] bigint NULL,
    [category_name] nvarchar(50) NOT NULL,
    [create_time] bigint NULL,
    [update_time] bigint NULL,
    [create_user] nvarchar(50) NOT NULL,
    [update_user] nvarchar(50) NOT NULL,
    [status] bit NULL,
    CONSTRAINT [PK_Categories] PRIMARY KEY ([category_id])
);
GO

CREATE TABLE [Configs] (
    [config_id] bigint NOT NULL IDENTITY,
    [config_name] nvarchar(50) NOT NULL,
    [create_time] bigint NULL,
    [update_time] bigint NULL,
    [create_user] nvarchar(50) NOT NULL,
    [update_user] nvarchar(50) NOT NULL,
    [status] bit NULL,
    [description] nvarchar(255) NOT NULL,
    CONSTRAINT [PK_Configs] PRIMARY KEY ([config_id])
);
GO

CREATE TABLE [Tags] (
    [tag_id] bigint NOT NULL IDENTITY,
    [tag_name] nvarchar(50) NOT NULL,
    [create_time] bigint NULL,
    [update_time] bigint NULL,
    [create_user] nvarchar(50) NOT NULL,
    [update_user] nvarchar(50) NOT NULL,
    [status] bit NULL,
    [description] nvarchar(255) NOT NULL,
    CONSTRAINT [PK_Tags] PRIMARY KEY ([tag_id])
);
GO

CREATE TABLE [Users] (
    [user_id] bigint NOT NULL IDENTITY,
    [email] varchar(50) NOT NULL,
    [password] varchar(max) NOT NULL,
    [fullname] nvarchar(50) NOT NULL,
    [image] varchar(max) NOT NULL,
    [gender] bit NULL,
    [dob] datetime NULL,
    [phone] varchar(50) NOT NULL,
    [address] nvarchar(50) NOT NULL,
    [create_time] bigint NOT NULL,
    [update_time] bigint NOT NULL,
    [role] varchar(50) NOT NULL,
    [status] bit NULL,
    CONSTRAINT [PK_Users] PRIMARY KEY ([user_id])
);
GO

CREATE TABLE [Articles] (
    [article_id] bigint NOT NULL IDENTITY,
    [title] nvarchar(255) NOT NULL,
    [content] nvarchar(max) NOT NULL,
    [create_time] bigint NULL,
    [update_time] bigint NULL,
    [create_user] nvarchar(50) NOT NULL,
    [update_user] nvarchar(50) NOT NULL,
    [fk_category_id] bigint NOT NULL,
    [status] bit NULL,
    CONSTRAINT [PK_Articles] PRIMARY KEY ([article_id]),
    CONSTRAINT [FK_Articles_Categories] FOREIGN KEY ([fk_category_id]) REFERENCES [Categories] ([category_id])
);
GO

CREATE TABLE [Articles_advertisement] (
    [fk_article_id] bigint NOT NULL,
    [fk_advertisement_id] bigint NOT NULL,
    CONSTRAINT [FK_tbl_articles_advertisement_tbl_advertisement] FOREIGN KEY ([fk_advertisement_id]) REFERENCES [Avertisement] ([advertisement_id]),
    CONSTRAINT [FK_tbl_articles_advertisement_tbl_articles] FOREIGN KEY ([fk_article_id]) REFERENCES [Articles] ([article_id])
);
GO

CREATE TABLE [Articles_tags] (
    [fk_article_id] bigint NOT NULL,
    [fk_tag_id] bigint NOT NULL,
    CONSTRAINT [FK_tbl_articles_tags_tbl_articles] FOREIGN KEY ([fk_article_id]) REFERENCES [Articles] ([article_id]),
    CONSTRAINT [FK_tbl_articles_tags_tbl_tags] FOREIGN KEY ([fk_tag_id]) REFERENCES [Tags] ([tag_id])
);
GO

CREATE TABLE [Comments] (
    [comment_id] bigint NOT NULL IDENTITY,
    [fk_article_id] bigint NOT NULL,
    [fk_user_id] bigint NOT NULL,
    [parent_comment_id] bigint NULL,
    [comment_content] nvarchar(255) NOT NULL,
    [create_time] bigint NULL,
    [update_time] bigint NULL,
    [like_number] int NULL,
    [report] bit NULL,
    CONSTRAINT [PK_Comments] PRIMARY KEY ([comment_id]),
    CONSTRAINT [FK_Comments_Articles] FOREIGN KEY ([fk_article_id]) REFERENCES [Articles] ([article_id]),
    CONSTRAINT [FK_Comments_Users] FOREIGN KEY ([fk_user_id]) REFERENCES [Users] ([user_id])
);
GO

CREATE TABLE [Interactions] (
    [interaction_id] bigint NOT NULL IDENTITY,
    [fk_user_id] bigint NOT NULL,
    [fk_article_id] bigint NOT NULL,
    [fk_action_type_id] bigint NOT NULL,
    [create_time] bigint NULL,
    [update_time] bigint NULL,
    [rate] int NULL,
    CONSTRAINT [PK_Interactions] PRIMARY KEY ([interaction_id]),
    CONSTRAINT [FK_Interactions_Articles] FOREIGN KEY ([fk_article_id]) REFERENCES [Articles] ([article_id]),
    CONSTRAINT [FK_Interactions_Users] FOREIGN KEY ([fk_user_id]) REFERENCES [Users] ([user_id]),
    CONSTRAINT [FK_tbl_interactions_tbl_action_type] FOREIGN KEY ([fk_action_type_id]) REFERENCES [Action_type] ([action_type_id])
);
GO

CREATE TABLE [Views] (
    [view_id] bigint NOT NULL IDENTITY,
    [fk_article_id] bigint NOT NULL,
    [fk_user_id] bigint NOT NULL,
    [create_time] bigint NOT NULL,
    CONSTRAINT [PK_Views] PRIMARY KEY ([view_id]),
    CONSTRAINT [FK_Views_Articles] FOREIGN KEY ([fk_article_id]) REFERENCES [Articles] ([article_id]),
    CONSTRAINT [FK_Views_Users] FOREIGN KEY ([fk_user_id]) REFERENCES [Users] ([user_id])
);
GO

CREATE INDEX [IX_Articles_fk_category_id] ON [Articles] ([fk_category_id]);
GO

CREATE INDEX [IX_Articles_advertisement_fk_advertisement_id] ON [Articles_advertisement] ([fk_advertisement_id]);
GO

CREATE INDEX [IX_Articles_advertisement_fk_article_id] ON [Articles_advertisement] ([fk_article_id]);
GO

CREATE INDEX [IX_Articles_tags_fk_article_id] ON [Articles_tags] ([fk_article_id]);
GO

CREATE INDEX [IX_Articles_tags_fk_tag_id] ON [Articles_tags] ([fk_tag_id]);
GO

CREATE INDEX [IX_Comments_fk_article_id] ON [Comments] ([fk_article_id]);
GO

CREATE INDEX [IX_Comments_fk_user_id] ON [Comments] ([fk_user_id]);
GO

CREATE INDEX [IX_Interactions_fk_action_type_id] ON [Interactions] ([fk_action_type_id]);
GO

CREATE INDEX [IX_Interactions_fk_article_id] ON [Interactions] ([fk_article_id]);
GO

CREATE INDEX [IX_Interactions_fk_user_id] ON [Interactions] ([fk_user_id]);
GO

CREATE INDEX [IX_Views_fk_article_id] ON [Views] ([fk_article_id]);
GO

CREATE INDEX [IX_Views_fk_user_id] ON [Views] ([fk_user_id]);
GO

INSERT INTO [__EFMigrationsHistory] ([MigrationId], [ProductVersion])
VALUES (N'20230620155637_Init', N'6.0.18');
GO

COMMIT;
GO

