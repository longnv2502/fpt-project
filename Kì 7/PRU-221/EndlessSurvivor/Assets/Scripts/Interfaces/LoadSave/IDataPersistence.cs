using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UnityEngine.Playables;

namespace Assets.Scripts.Interfaces.LoadSave
{
    internal interface IDataPersistence
    {
        void load();

        void save();
    }
}
