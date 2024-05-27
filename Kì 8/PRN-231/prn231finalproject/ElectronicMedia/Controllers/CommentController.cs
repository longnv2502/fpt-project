using AutoMapper;
using AutoMapper.QueryableExtensions;
using ElectronicMedia.Dtos;
using ElectronicMedia.Dtos.CommentDTO;
using ElectronicMedia.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace ElectronicMedia.Controllers
{
    [ApiController]
    [Route("/[controller]/[action]")]
    public class CommentController : Controller
    {
        private readonly ElectronicNewspaperPageContext _context;
        private MapperConfiguration config;
        private IMapper mapper;

        public CommentController(ElectronicNewspaperPageContext context)
        {
            _context = context;
            config = new MapperConfiguration(cfg => cfg.AddProfile(new MapperProfile()));
            mapper = config.CreateMapper();
        }

        [HttpGet]
        public ActionResult getAllCommentsFromArticle(long articleID)
        {
            List<CommentsResponseDto> listComments = GetAllCommentsByArticleID(articleID);
            if (listComments == null)
            {
                return Ok(null);
            }
            return Ok(listComments);
        }

        [HttpPost]
        public ActionResult CreateNewComment(NewCommentDto commentDto)
        {
            if (ModelState.IsValid)
            {
                var newComment = new Comment
                {
                    FkArticleId = commentDto.FkArticleId,
                    ParentCommentId = commentDto.ParentCommentId,
                    CommentContent = commentDto.CommentContent,
                    FkUserId = commentDto.FkUserId,
                    CreateTime = DateTime.Now,
                    UpdateTime = DateTime.Now,
                    LikeNumber = 0,
                    Report = false
                };
                _context.Comments.Add(newComment);
                _context.SaveChanges();
                return Ok();
            }
            return Accepted();
        }

        [HttpPost]
        public ActionResult UpdateComment(long articleID, long commentID, String updatedCommentContent)
        {
            try
            {
                var comment = GetSpecificComment(articleID, commentID);
                comment.CommentContent = updatedCommentContent;
                comment.UpdateTime = DateTime.Now;
                _context.SaveChanges();
            }
            catch (Exception e)
            {
                return StatusCode(500);
            }
            return Ok();
        }

        [HttpDelete]
        public ActionResult DeleteComment(long articleID, long commentID)
        {
            try
            {
                var comments = _context.Comments.Include(x => x.ChildrenComments).Where(y => y.FkArticleId == articleID).ToList();
                var flatten = Flatten(comments.Where(x => x.CommentId == commentID));
                _context.Comments.RemoveRange(flatten);
                _context.SaveChangesAsync();
            }
            catch (Exception e)
            {
                return StatusCode(500);
            }
            return Ok();
        }

        [HttpPost]
        public ActionResult RateComment(long articleID, long commentID)
        {
            try
            {
                var comment = GetSpecificComment(articleID, commentID);
                comment.LikeNumber++;
                _context.SaveChanges();
            }
            catch (Exception e)
            {
                return StatusCode(500);
            }
            return Ok();
        }

        IEnumerable<Comment> Flatten(IEnumerable<Comment> comments) =>
        comments.SelectMany(x => Flatten(x.ChildrenComments)).Concat(comments);

        private Comment GetSpecificComment(long articleID, long commentID)
        {
            Comment comment = new Comment();
            try
            {
                comment = _context.Comments.FirstOrDefault(x => x.FkArticleId == articleID && x.CommentId == commentID);
            }
            catch (Exception e)
            {
                return null;
            }
            return comment;
        }

        private List<CommentsResponseDto> GetAllCommentsByArticleID(long articleID)
        {
            List<CommentsResponseDto> comments = new List<CommentsResponseDto>();
            try
            {
                comments = _context.Comments.Include(c => c.ChildrenComments).Include(a => a.FkArticle).Include(u => u.FkUser).Where(c => c.FkArticleId == articleID).AsNoTrackingWithIdentityResolution().ProjectTo<CommentsResponseDto>(config).ToList();
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error while getting all comments from article", ex);
            }
            if (comments.Count == 0)
            {
                return null;
            }
            //Structure comments into a tree
            List<CommentsResponseDto> rootComments = comments.Where(c => c.ParentCommentId == null).AsParallel().ToList();
            return rootComments;
        }
    }
}
