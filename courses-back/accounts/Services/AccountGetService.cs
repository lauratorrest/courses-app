using accounts.Models;
using MongoDB.Driver;
using accounts.DatabaseSettings;

namespace accounts.Services
{
    public class AccountGetService
    {

        private readonly IMongoCollection<Account> _accountsCollection;

        public AccountGetService(MongoDBContext mongoDBContext)
        {
            _accountsCollection = mongoDBContext.AccountsCollection;
        }

        //GET
        public async Task<List<Account>> Get() =>
            await _accountsCollection.Find(_ => true).ToListAsync();

        public async Task<Account?> Get(string id) =>
            await _accountsCollection.Find(account => account.Id == id).FirstOrDefaultAsync();
    }
}
