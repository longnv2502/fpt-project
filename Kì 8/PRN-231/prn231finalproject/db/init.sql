USE [master]
GO
/****** Object:  Database [ElectronicNewspaperPage]    Script Date: 6/21/2023 12:27:47 AM ******/
CREATE DATABASE [ElectronicNewspaperPage]
GO
ALTER DATABASE [ElectronicNewspaperPage] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET ARITHABORT OFF 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET  ENABLE_BROKER 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET  MULTI_USER 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ElectronicNewspaperPage] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [ElectronicNewspaperPage] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'ElectronicNewspaperPage', N'ON'
GO
ALTER DATABASE [ElectronicNewspaperPage] SET QUERY_STORE = OFF
GO
USE [ElectronicNewspaperPage]
GO
/****** Object:  Table [dbo].[__EFMigrationsHistory]    Script Date: 6/21/2023 12:27:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[__EFMigrationsHistory](
	[MigrationId] [nvarchar](150) NOT NULL,
	[ProductVersion] [nvarchar](32) NOT NULL,
 CONSTRAINT [PK___EFMigrationsHistory] PRIMARY KEY CLUSTERED 
(
	[MigrationId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Action_type]    Script Date: 6/21/2023 12:27:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Action_type](
	[action_type_id] [bigint] IDENTITY(1,1) NOT NULL,
	[action_name] [nvarchar](50) NOT NULL,
	[create_time] [datetime] NULL,
	[update_time] [datetime] NULL,
	[create_user] [nvarchar](50) NULL,
	[update_user] [nvarchar](50) NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_tbl_action_type] PRIMARY KEY CLUSTERED 
(
	[action_type_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Articles]    Script Date: 6/21/2023 12:27:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Articles](
	[article_id] [bigint] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](255) NOT NULL,
	[content] [nvarchar](max) NOT NULL,
	[create_time] [datetime] NULL,
	[update_time] [datetime] NULL,
	[create_user] [nvarchar](50) NULL,
	[update_user] [nvarchar](50) NULL,
	[fk_category_id] [bigint] NOT NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_Articles] PRIMARY KEY CLUSTERED 
(
	[article_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Articles_Advertisement]    Script Date: 6/21/2023 12:27:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Articles_Advertisement](
	[fk_article_id] [bigint] NOT NULL,
	[fk_advertisement_id] [bigint] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Articles_tags]    Script Date: 6/21/2023 12:27:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Articles_tags](
	[fk_article_id] [bigint] NOT NULL,
	[fk_tag_id] [bigint] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Avertisement]    Script Date: 6/21/2023 12:27:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Avertisement](
	[advertisement_id] [bigint] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](50) NOT NULL,
	[image_url] [varchar](255) NOT NULL,
	[target_url] [varchar](255) NOT NULL,
	[create_time] [datetime] NULL,
	[update_time] [datetime] NULL,
	[create_user] [nvarchar](50) NULL,
	[update_user] [nvarchar](50) NULL,
	[status] [bit] NULL,
	[ad_api] [varchar](255) NOT NULL,
 CONSTRAINT [PK_Advertisement] PRIMARY KEY CLUSTERED 
(
	[advertisement_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 6/21/2023 12:27:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[category_id] [bigint] IDENTITY(1,1) NOT NULL,
	[parent_category_id] [bigint] NULL,
	[category_name] [nvarchar](50) NOT NULL,
	[create_time] [datetime] NULL,
	[update_time] [datetime] NULL,
	[create_user] [nvarchar](50) NULL,
	[update_user] [nvarchar](50) NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_Categories] PRIMARY KEY CLUSTERED 
(
	[category_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Comments]    Script Date: 6/21/2023 12:27:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comments](
	[comment_id] [bigint] IDENTITY(1,1) NOT NULL,
	[fk_article_id] [bigint] NOT NULL,
	[fk_user_id] [bigint] NOT NULL,
	[parent_comment_id] [bigint] NULL,
	[comment_content] [nvarchar](255) NOT NULL,
	[create_time] [datetime] NULL,
	[update_time] [datetime] NULL,
	[like_number] [int] NULL,
	[report] [bit] NULL,
 CONSTRAINT [PK_Comments] PRIMARY KEY CLUSTERED 
(
	[comment_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Configs]    Script Date: 6/21/2023 12:27:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Configs](
	[config_id] [bigint] IDENTITY(1,1) NOT NULL,
	[config_name] [nvarchar](50) NOT NULL,
	[create_time] [datetime] NULL,
	[update_time] [datetime] NULL,
	[create_user] [nvarchar](50) NULL,
	[update_user] [nvarchar](50) NULL,
	[status] [bit] NULL,
	[description] [nvarchar](255) NULL,
 CONSTRAINT [PK_Configs] PRIMARY KEY CLUSTERED 
(
	[config_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Interactions]    Script Date: 6/21/2023 12:27:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Interactions](
	[interaction_id] [bigint] IDENTITY(1,1) NOT NULL,
	[fk_user_id] [bigint] NOT NULL,
	[fk_article_id] [bigint] NOT NULL,
	[fk_action_type_id] [bigint] NOT NULL,
	[create_time] [datetime] NULL,
	[update_time] [datetime] NULL,
	[rate] [int] NULL,
 CONSTRAINT [PK_Interactions] PRIMARY KEY CLUSTERED 
(
	[interaction_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tags]    Script Date: 6/21/2023 12:27:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tags](
	[tag_id] [bigint] IDENTITY(1,1) NOT NULL,
	[tag_name] [nvarchar](50) NOT NULL,
	[create_time] [datetime] NULL,
	[update_time] [datetime] NULL,
	[create_user] [nvarchar](50) NULL,
	[update_user] [nvarchar](50) NULL,
	[status] [bit] NULL,
	[description] [nvarchar](255) NULL,
 CONSTRAINT [PK_Tags] PRIMARY KEY CLUSTERED 
(
	[tag_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 6/21/2023 12:27:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[user_id] [bigint] IDENTITY(1,1) NOT NULL,
	[username] [varchar](32) NOT NULL,
	[email] [varchar](50) NOT NULL,
	[fullname] [nvarchar](50) NULL,
	[image] [varchar](max) NULL,
	[gender] [bit] NULL,
	[dob] [datetime] NULL,
	[phone] [varchar](50) NULL,
	[address] [nvarchar](50) NULL,
	[create_time] [datetime] NOT NULL,
	[update_time] [datetime] NOT NULL,
	[role] [varchar](50) NOT NULL,
	[status] [bit] NULL,
	[password-hash] [varbinary](max) NOT NULL,
	[password-salt] [varbinary](max) NOT NULL,
	[refresh-token] [nvarchar](max) NOT NULL,
	[token-created] [datetime] NOT NULL,
	[token-expires] [datetime] NOT NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Views]    Script Date: 6/21/2023 12:27:48 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Views](
	[view_id] [bigint] IDENTITY(1,1) NOT NULL,
	[fk_article_id] [bigint] NOT NULL,
	[fk_user_id] [bigint] NOT NULL,
	[create_time] [datetime] NOT NULL,
 CONSTRAINT [PK_Views] PRIMARY KEY CLUSTERED 
(
	[view_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[__EFMigrationsHistory] ([MigrationId], [ProductVersion]) VALUES (N'20230620173007_InitDB', N'7.0.3')
GO
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF__Users__username__778AC167]  DEFAULT (N'') FOR [username]
GO
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF__Users__password-__72C60C4A]  DEFAULT (0x) FOR [password-hash]
GO
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF__Users__password-__73BA3083]  DEFAULT (0x) FOR [password-salt]
GO
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF__Users__refresh-t__74AE54BC]  DEFAULT (N'') FOR [refresh-token]
GO
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF__Users__token-cre__75A278F5]  DEFAULT (CONVERT([bigint],(0))) FOR [token-created]
GO
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF__Users__token-exp__76969D2E]  DEFAULT (CONVERT([bigint],(0))) FOR [token-expires]
GO
ALTER TABLE [dbo].[Articles]  WITH CHECK ADD  CONSTRAINT [FK_Articles_Categories] FOREIGN KEY([fk_category_id])
REFERENCES [dbo].[Categories] ([category_id])
GO
ALTER TABLE [dbo].[Articles] CHECK CONSTRAINT [FK_Articles_Categories]
GO
ALTER TABLE [dbo].[Articles_Advertisement]  WITH CHECK ADD  CONSTRAINT [FK_tbl_articles_advertisement_tbl_advertisement] FOREIGN KEY([fk_advertisement_id])
REFERENCES [dbo].[Avertisement] ([advertisement_id])
GO
ALTER TABLE [dbo].[Articles_Advertisement] CHECK CONSTRAINT [FK_tbl_articles_advertisement_tbl_advertisement]
GO
ALTER TABLE [dbo].[Articles_Advertisement]  WITH CHECK ADD  CONSTRAINT [FK_tbl_articles_advertisement_tbl_articles] FOREIGN KEY([fk_article_id])
REFERENCES [dbo].[Articles] ([article_id])
GO
ALTER TABLE [dbo].[Articles_Advertisement] CHECK CONSTRAINT [FK_tbl_articles_advertisement_tbl_articles]
GO
ALTER TABLE [dbo].[Articles_tags]  WITH CHECK ADD  CONSTRAINT [FK_tbl_articles_tags_tbl_articles] FOREIGN KEY([fk_article_id])
REFERENCES [dbo].[Articles] ([article_id])
GO
ALTER TABLE [dbo].[Articles_tags] CHECK CONSTRAINT [FK_tbl_articles_tags_tbl_articles]
GO
ALTER TABLE [dbo].[Articles_tags]  WITH CHECK ADD  CONSTRAINT [FK_tbl_articles_tags_tbl_tags] FOREIGN KEY([fk_tag_id])
REFERENCES [dbo].[Tags] ([tag_id])
GO
ALTER TABLE [dbo].[Articles_tags] CHECK CONSTRAINT [FK_tbl_articles_tags_tbl_tags]
GO
ALTER TABLE [dbo].[Comments]  WITH CHECK ADD  CONSTRAINT [FK_Comments_Articles] FOREIGN KEY([fk_article_id])
REFERENCES [dbo].[Articles] ([article_id])
GO
ALTER TABLE [dbo].[Comments] CHECK CONSTRAINT [FK_Comments_Articles]
GO
ALTER TABLE [dbo].[Comments]  WITH CHECK ADD  CONSTRAINT [FK_Comments_Users] FOREIGN KEY([fk_user_id])
REFERENCES [dbo].[Users] ([user_id])
GO
ALTER TABLE [dbo].[Comments] CHECK CONSTRAINT [FK_Comments_Users]
GO
ALTER TABLE [dbo].[Interactions]  WITH CHECK ADD  CONSTRAINT [FK_Interactions_Articles] FOREIGN KEY([fk_article_id])
REFERENCES [dbo].[Articles] ([article_id])
GO
ALTER TABLE [dbo].[Interactions] CHECK CONSTRAINT [FK_Interactions_Articles]
GO
ALTER TABLE [dbo].[Interactions]  WITH CHECK ADD  CONSTRAINT [FK_Interactions_Users] FOREIGN KEY([fk_user_id])
REFERENCES [dbo].[Users] ([user_id])
GO
ALTER TABLE [dbo].[Interactions] CHECK CONSTRAINT [FK_Interactions_Users]
GO
ALTER TABLE [dbo].[Interactions]  WITH CHECK ADD  CONSTRAINT [FK_tbl_interactions_tbl_action_type] FOREIGN KEY([fk_action_type_id])
REFERENCES [dbo].[Action_type] ([action_type_id])
GO
ALTER TABLE [dbo].[Interactions] CHECK CONSTRAINT [FK_tbl_interactions_tbl_action_type]
GO
ALTER TABLE [dbo].[Views]  WITH CHECK ADD  CONSTRAINT [FK_Views_Articles] FOREIGN KEY([fk_article_id])
REFERENCES [dbo].[Articles] ([article_id])
GO
ALTER TABLE [dbo].[Views] CHECK CONSTRAINT [FK_Views_Articles]
GO
ALTER TABLE [dbo].[Views]  WITH CHECK ADD  CONSTRAINT [FK_Views_Users] FOREIGN KEY([fk_user_id])
REFERENCES [dbo].[Users] ([user_id])
GO
ALTER TABLE [dbo].[Views] CHECK CONSTRAINT [FK_Views_Users]
GO
USE [master]
GO
ALTER DATABASE [ElectronicNewspaperPage] SET  READ_WRITE 
GO
