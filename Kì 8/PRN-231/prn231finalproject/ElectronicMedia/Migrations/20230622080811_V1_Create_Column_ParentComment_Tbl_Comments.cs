using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace ElectronicMedia.Migrations
{
    public partial class V1_Create_Column_ParentComment_Tbl_Comments : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateIndex(
                name: "IX_Comments_parent_comment_id",
                table: "Comments",
                column: "parent_comment_id");

            migrationBuilder.AddForeignKey(
                name: "FK_Comments_Comments_parent_comment_id",
                table: "Comments",
                column: "parent_comment_id",
                principalTable: "Comments",
                principalColumn: "comment_id");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Comments_Comments_parent_comment_id",
                table: "Comments");

            migrationBuilder.DropIndex(
                name: "IX_Comments_parent_comment_id",
                table: "Comments");
        }
    }
}
