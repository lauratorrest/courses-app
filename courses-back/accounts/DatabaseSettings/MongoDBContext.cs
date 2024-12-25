using accounts.Models;
using Microsoft.Extensions.Options;
using MongoDB.Driver;

namespace accounts.DatabaseSettings
{
    public class MongoDBContext
    {

        private readonly IMongoDatabase _mongoDatabase;

        public MongoDBContext(IOptions<AccountsDatabaseSettings> accountsDatabaseSettings)
        {
            var mongoClient = new MongoClient(accountsDatabaseSettings.Value.ConnectionString);
            _mongoDatabase = mongoClient.GetDatabase(accountsDatabaseSettings.Value.DatabaseName);
        }

        public IMongoCollection<Account> AccountsCollection =>
            _mongoDatabase.GetCollection<Account>("accounts");
    }
}
