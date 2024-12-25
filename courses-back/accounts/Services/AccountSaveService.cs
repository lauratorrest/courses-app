using accounts.DatabaseSettings;
using accounts.Models;
using accounts.Models.Enums;
using MongoDB.Driver;

namespace accounts.Services
{
    public class AccountSaveService
    {

        private readonly IMongoCollection<Account> _accountsCollection;

        public AccountSaveService(MongoDBContext mongoDBContext)
        {
            _accountsCollection = mongoDBContext.AccountsCollection;
        }

        //SAVE
        public async Task SaveAccount(Account account)
        {
            account.CreatedDate = DateTime.Now;
            account.Status = AccountStatus.ACTIVE.ToString();
            account.Type = AccountType.PERSONAL.ToString();
            await _accountsCollection.InsertOneAsync(account);
        }
    }
}
