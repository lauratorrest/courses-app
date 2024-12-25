using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace accounts.Models
{
    public class Account
    {

        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string? Id {  get; set; }
        public string UserId { get; set; } = null!;
        public string AuthId { get; set; } = null!;
        public DateTime CreatedDate { get; set; }
        public DateTime UpdateDate { get; set; }
        public string Status { get; set; } = null!;
        public string Type { get; set; } = null!;
    }
}
