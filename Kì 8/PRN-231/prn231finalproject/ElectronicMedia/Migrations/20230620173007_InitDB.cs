using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace ElectronicMedia.Migrations
{
    public partial class InitDB : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Action_type",
                columns: table => new
                {
                    action_type_id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    action_name = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: false),
                    create_time = table.Column<DateTime>(type: "datetime", nullable: true),
                    update_time = table.Column<DateTime>(type: "datetime", nullable: true),
                    create_user = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: true),
                    update_user = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: true),
                    status = table.Column<bool>(type: "bit", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Action_type", x => x.action_type_id);
                });

            migrationBuilder.CreateTable(
                name: "Avertisement",
                columns: table => new
                {
                    advertisement_id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    title = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: false),
                    image_url = table.Column<string>(type: "varchar(255)", unicode: false, maxLength: 255, nullable: false),
                    target_url = table.Column<string>(type: "varchar(255)", unicode: false, maxLength: 255, nullable: false),
                    create_time = table.Column<DateTime>(type: "datetime", nullable: true),
                    update_time = table.Column<DateTime>(type: "datetime", nullable: true),
                    create_user = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: true),
                    update_user = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: true),
                    status = table.Column<bool>(type: "bit", nullable: true),
                    ad_api = table.Column<string>(type: "varchar(255)", unicode: false, maxLength: 255, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Advertisement", x => x.advertisement_id);
                });

            migrationBuilder.CreateTable(
                name: "Categories",
                columns: table => new
                {
                    category_id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    parent_category_id = table.Column<long>(type: "bigint", nullable: true),
                    category_name = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: false),
                    create_time = table.Column<DateTime>(type: "datetime", nullable: true),
                    update_time = table.Column<DateTime>(type: "datetime", nullable: true),
                    create_user = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: true),
                    update_user = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: true),
                    status = table.Column<bool>(type: "bit", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Categories", x => x.category_id);
                });

            migrationBuilder.CreateTable(
                name: "Configs",
                columns: table => new
                {
                    config_id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    config_name = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: false),
                    create_time = table.Column<DateTime>(type: "datetime", nullable: true),
                    update_time = table.Column<DateTime>(type: "datetime", nullable: true),
                    create_user = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: true),
                    update_user = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: true),
                    status = table.Column<bool>(type: "bit", nullable: true),
                    description = table.Column<string>(type: "nvarchar(255)", maxLength: 255, nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Configs", x => x.config_id);
                });

            migrationBuilder.CreateTable(
                name: "Tags",
                columns: table => new
                {
                    tag_id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    tag_name = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: false),
                    create_time = table.Column<DateTime>(type: "datetime", nullable: true),
                    update_time = table.Column<DateTime>(type: "datetime", nullable: true),
                    create_user = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: true),
                    update_user = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: true),
                    status = table.Column<bool>(type: "bit", nullable: true),
                    description = table.Column<string>(type: "nvarchar(255)", maxLength: 255, nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Tags", x => x.tag_id);
                });

            migrationBuilder.CreateTable(
                name: "Users",
                columns: table => new
                {
                    user_id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    username = table.Column<string>(type: "varchar(32)", unicode: false, maxLength: 32, nullable: false, defaultValueSql: "(N'')"),
                    email = table.Column<string>(type: "varchar(50)", unicode: false, maxLength: 50, nullable: false),
                    fullname = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: true),
                    image = table.Column<string>(type: "varchar(max)", unicode: false, nullable: true),
                    gender = table.Column<bool>(type: "bit", nullable: true),
                    dob = table.Column<DateTime>(type: "datetime", nullable: true),
                    phone = table.Column<string>(type: "varchar(50)", unicode: false, maxLength: 50, nullable: true),
                    address = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: true),
                    create_time = table.Column<DateTime>(type: "datetime", nullable: false),
                    update_time = table.Column<DateTime>(type: "datetime", nullable: false),
                    role = table.Column<string>(type: "varchar(50)", unicode: false, maxLength: 50, nullable: false),
                    status = table.Column<bool>(type: "bit", nullable: true),
                    passwordhash = table.Column<byte[]>(name: "password-hash", type: "varbinary(max)", nullable: false, defaultValueSql: "(0x)"),
                    passwordsalt = table.Column<byte[]>(name: "password-salt", type: "varbinary(max)", nullable: false, defaultValueSql: "(0x)"),
                    refreshtoken = table.Column<string>(name: "refresh-token", type: "nvarchar(max)", nullable: false, defaultValueSql: "(N'')"),
                    tokencreated = table.Column<DateTime>(name: "token-created", type: "datetime", nullable: false, defaultValueSql: "(CONVERT([bigint],(0)))"),
                    tokenexpires = table.Column<DateTime>(name: "token-expires", type: "datetime", nullable: false, defaultValueSql: "(CONVERT([bigint],(0)))")
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Users", x => x.user_id);
                });

            migrationBuilder.CreateTable(
                name: "Articles",
                columns: table => new
                {
                    article_id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    title = table.Column<string>(type: "nvarchar(255)", maxLength: 255, nullable: false),
                    content = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    create_time = table.Column<DateTime>(type: "datetime", nullable: true),
                    update_time = table.Column<DateTime>(type: "datetime", nullable: true),
                    create_user = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: true),
                    update_user = table.Column<string>(type: "nvarchar(50)", maxLength: 50, nullable: true),
                    fk_category_id = table.Column<long>(type: "bigint", nullable: false),
                    status = table.Column<bool>(type: "bit", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Articles", x => x.article_id);
                    table.ForeignKey(
                        name: "FK_Articles_Categories",
                        column: x => x.fk_category_id,
                        principalTable: "Categories",
                        principalColumn: "category_id");
                });

            migrationBuilder.CreateTable(
                name: "Articles_Advertisement",
                columns: table => new
                {
                    fk_article_id = table.Column<long>(type: "bigint", nullable: false),
                    fk_advertisement_id = table.Column<long>(type: "bigint", nullable: false)
                },
                constraints: table =>
                {
                    table.ForeignKey(
                        name: "FK_tbl_articles_advertisement_tbl_advertisement",
                        column: x => x.fk_advertisement_id,
                        principalTable: "Avertisement",
                        principalColumn: "advertisement_id");
                    table.ForeignKey(
                        name: "FK_tbl_articles_advertisement_tbl_articles",
                        column: x => x.fk_article_id,
                        principalTable: "Articles",
                        principalColumn: "article_id");
                });

            migrationBuilder.CreateTable(
                name: "Articles_tags",
                columns: table => new
                {
                    fk_article_id = table.Column<long>(type: "bigint", nullable: false),
                    fk_tag_id = table.Column<long>(type: "bigint", nullable: false)
                },
                constraints: table =>
                {
                    table.ForeignKey(
                        name: "FK_tbl_articles_tags_tbl_articles",
                        column: x => x.fk_article_id,
                        principalTable: "Articles",
                        principalColumn: "article_id");
                    table.ForeignKey(
                        name: "FK_tbl_articles_tags_tbl_tags",
                        column: x => x.fk_tag_id,
                        principalTable: "Tags",
                        principalColumn: "tag_id");
                });

            migrationBuilder.CreateTable(
                name: "Comments",
                columns: table => new
                {
                    comment_id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    fk_article_id = table.Column<long>(type: "bigint", nullable: false),
                    fk_user_id = table.Column<long>(type: "bigint", nullable: false),
                    parent_comment_id = table.Column<long>(type: "bigint", nullable: true),
                    comment_content = table.Column<string>(type: "nvarchar(255)", maxLength: 255, nullable: false),
                    create_time = table.Column<DateTime>(type: "datetime", nullable: true),
                    update_time = table.Column<DateTime>(type: "datetime", nullable: true),
                    like_number = table.Column<int>(type: "int", nullable: true),
                    report = table.Column<bool>(type: "bit", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Comments", x => x.comment_id);
                    table.ForeignKey(
                        name: "FK_Comments_Articles",
                        column: x => x.fk_article_id,
                        principalTable: "Articles",
                        principalColumn: "article_id");
                    table.ForeignKey(
                        name: "FK_Comments_Users",
                        column: x => x.fk_user_id,
                        principalTable: "Users",
                        principalColumn: "user_id");
                });

            migrationBuilder.CreateTable(
                name: "Interactions",
                columns: table => new
                {
                    interaction_id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    fk_user_id = table.Column<long>(type: "bigint", nullable: false),
                    fk_article_id = table.Column<long>(type: "bigint", nullable: false),
                    fk_action_type_id = table.Column<long>(type: "bigint", nullable: false),
                    create_time = table.Column<DateTime>(type: "datetime", nullable: true),
                    update_time = table.Column<DateTime>(type: "datetime", nullable: true),
                    rate = table.Column<int>(type: "int", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Interactions", x => x.interaction_id);
                    table.ForeignKey(
                        name: "FK_Interactions_Articles",
                        column: x => x.fk_article_id,
                        principalTable: "Articles",
                        principalColumn: "article_id");
                    table.ForeignKey(
                        name: "FK_Interactions_Users",
                        column: x => x.fk_user_id,
                        principalTable: "Users",
                        principalColumn: "user_id");
                    table.ForeignKey(
                        name: "FK_tbl_interactions_tbl_action_type",
                        column: x => x.fk_action_type_id,
                        principalTable: "Action_type",
                        principalColumn: "action_type_id");
                });

            migrationBuilder.CreateTable(
                name: "Views",
                columns: table => new
                {
                    view_id = table.Column<long>(type: "bigint", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    fk_article_id = table.Column<long>(type: "bigint", nullable: false),
                    fk_user_id = table.Column<long>(type: "bigint", nullable: false),
                    create_time = table.Column<DateTime>(type: "datetime", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Views", x => x.view_id);
                    table.ForeignKey(
                        name: "FK_Views_Articles",
                        column: x => x.fk_article_id,
                        principalTable: "Articles",
                        principalColumn: "article_id");
                    table.ForeignKey(
                        name: "FK_Views_Users",
                        column: x => x.fk_user_id,
                        principalTable: "Users",
                        principalColumn: "user_id");
                });

            migrationBuilder.CreateIndex(
                name: "IX_Articles_fk_category_id",
                table: "Articles",
                column: "fk_category_id");

            migrationBuilder.CreateIndex(
                name: "IX_Articles_Advertisement_fk_advertisement_id",
                table: "Articles_Advertisement",
                column: "fk_advertisement_id");

            migrationBuilder.CreateIndex(
                name: "IX_Articles_Advertisement_fk_article_id",
                table: "Articles_Advertisement",
                column: "fk_article_id");

            migrationBuilder.CreateIndex(
                name: "IX_Articles_tags_fk_article_id",
                table: "Articles_tags",
                column: "fk_article_id");

            migrationBuilder.CreateIndex(
                name: "IX_Articles_tags_fk_tag_id",
                table: "Articles_tags",
                column: "fk_tag_id");

            migrationBuilder.CreateIndex(
                name: "IX_Comments_fk_article_id",
                table: "Comments",
                column: "fk_article_id");

            migrationBuilder.CreateIndex(
                name: "IX_Comments_fk_user_id",
                table: "Comments",
                column: "fk_user_id");

            migrationBuilder.CreateIndex(
                name: "IX_Interactions_fk_action_type_id",
                table: "Interactions",
                column: "fk_action_type_id");

            migrationBuilder.CreateIndex(
                name: "IX_Interactions_fk_article_id",
                table: "Interactions",
                column: "fk_article_id");

            migrationBuilder.CreateIndex(
                name: "IX_Interactions_fk_user_id",
                table: "Interactions",
                column: "fk_user_id");

            migrationBuilder.CreateIndex(
                name: "IX_Views_fk_article_id",
                table: "Views",
                column: "fk_article_id");

            migrationBuilder.CreateIndex(
                name: "IX_Views_fk_user_id",
                table: "Views",
                column: "fk_user_id");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Articles_Advertisement");

            migrationBuilder.DropTable(
                name: "Articles_tags");

            migrationBuilder.DropTable(
                name: "Comments");

            migrationBuilder.DropTable(
                name: "Configs");

            migrationBuilder.DropTable(
                name: "Interactions");

            migrationBuilder.DropTable(
                name: "Views");

            migrationBuilder.DropTable(
                name: "Avertisement");

            migrationBuilder.DropTable(
                name: "Tags");

            migrationBuilder.DropTable(
                name: "Action_type");

            migrationBuilder.DropTable(
                name: "Articles");

            migrationBuilder.DropTable(
                name: "Users");

            migrationBuilder.DropTable(
                name: "Categories");
        }
    }
}
