using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assets.Scripts.Interfaces.MainCharacter
{
    public interface ICharacterStats
    {
        void takeDmg(float dmgTaken);

        void useSkill(int manaUsed);

        void expUpdate(float monsterExp);

        void lvlUdpate();

        void Die();
    }
}
