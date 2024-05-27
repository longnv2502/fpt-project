
using Assets.Scripts.Interfaces.LoadSave;
using Assets.Scripts.Interfaces.MainCharacter;
using System;
using UnityEngine;
using UnityEngine.UI;
using static UnityEngine.EventSystems.EventTrigger;

public class CharacterStats : MonoBehaviour, IDataPersistence, ICharacterStats
{
    private Animator anim;
    public float currentHp { get; set; }
    public float currentMana { get; set; }
    public float currentexp { get; set; }
    public int currentlvl { get; set; }
    float requiredExp = 100f;
    public Stat hp;
    public Stat mana;
    public Stat dmg;
    public Stat def;
    public Stat spd;
    public int coinAmount;
    /*    public Transform effectPos;
        public GameObject healingEffect;
        public GameObject manaEffect;*/
    public Image hpBar;
    public Image manaBar;
    public Image expBar;
    private float finalDmgTaken;
    public float pushForce = 10f;
    private Rigidbody2D rb;

    public AudioSource audioSource;
    public AudioClip hurtSound;
    public AudioClip lvlUpSound;

    private void Start()
    {
        anim = GetComponent<Animator>();
        currentlvl = 1;
        try
        {
            hpBar.fillAmount = currentHp / hp.getValue();
            manaBar.fillAmount = currentMana / mana.getValue();
            expBar.fillAmount = currentexp / requiredExp;
        }
        catch (ArithmeticException e)
        {
            Debug.Log("Error while starting the game: " + e.Message);
        }
    }

    public void takeDmg(float dmgTaken)
    {
        try
        {
            finalDmgTaken = dmgTaken * 100 / (100 + def.getValue());
            finalDmgTaken = Mathf.Clamp(finalDmgTaken, 0, float.MaxValue);
            currentHp -= finalDmgTaken;
            hpBar.fillAmount = currentHp / hp.getValue();
            if (currentHp <= 0)
            {
                Die();
            }
        }
        catch (ArithmeticException e)
        {
            Debug.Log("Error while execute method takeDmg: " + e.Message);
        }
    }

    public void useSkill(int manaUsed)
    {
        try
        {
            currentMana -= manaUsed;
            manaBar.fillAmount = currentMana / mana.getValue();
        }
        catch (ArithmeticException e)
        {
            Debug.Log("Error while execute method useSkill: " + e.Message);
        }
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if ((transform.position - collision.transform.position).magnitude < 1.5f)
        {
            if (collision.gameObject.CompareTag("BloodPotion"))
            {

                currentHp += 10;
                if (currentHp >= hp.getValue())
                {
                    currentHp = hp.getValue();
                }
                //Debug.Log(currentHp);
                hpBar.fillAmount = currentHp / hp.getValue();
                Destroy(collision.gameObject);
            }
            if (collision.gameObject.CompareTag("ManaPotion"))
            {
                currentMana += 10;
                if (currentMana >= mana.getValue())
                {
                    currentMana = mana.getValue();
                }
                manaBar.fillAmount = currentMana / mana.getValue();
                Destroy(collision.gameObject);
            }
            if (collision.gameObject.CompareTag("Exp"))
            {
                expUpdate(5f);
                Destroy(collision.gameObject);
            }
            if (collision.gameObject.CompareTag("Coin"))
            {
                //Code for MC interact with Coin Item here

            }
            if (collision.gameObject.tag.Contains("Enemy"))
            {
                //Code for MC interact with Enemy here

            }
            else
            {
                anim.SetBool("isHurt", false);
            }
        }
    }

    private void Update()
    {

    }

    public void Die()
    {
        Destroy(gameObject);
        Time.timeScale = 0;
    }

    public void expUpdate(float monsterExp)
    {
        try
        {
            currentexp += monsterExp;
            expBar.fillAmount = currentexp / requiredExp;
            if (currentexp >= requiredExp)
            {
                lvlUdpate();
                //Update EXP UI here
            }
        }
        catch (ArithmeticException e)
        {
            Debug.Log("Error while execute method expUpdate: " + e.Message);
        }
    }

    public void lvlUdpate()
    {
        audioSource.PlayOneShot(lvlUpSound, 1);
        currentlvl++;
        try
        {
            currentHp += hp.getValue() * 10 / 100;
            hp.addModifier(hp.getValue() * 10 / 100);
            dmg.addModifier(dmg.getValue() * 10 / 100);
            def.addModifier(def.getValue() * 10 / 100);
            if (currentlvl < 10)
            {
                requiredExp *= (float)110 / 100;
            }
            if (currentlvl >= 10 && currentlvl < 20)
            {
                requiredExp *= (float)115 / 100;
            }
            if (currentlvl >= 20)
            {
                requiredExp *= (float)120 / 100;
            }
        }
        catch (ArithmeticException e)
        {
            Debug.Log("Error while execute method lvlUdpate: " + e.Message);
        }
        currentexp = 0;
        //Debug.Log("Required EXP: " + requiredExp);
        //Debug.Log("Current EXP: " + currentexp);
        //Debug.Log("New DMG: " + dmg.getValue());
        //Debug.Log("New DEF: " + def.getValue());
        //Debug.Log("New HP: " + hp.getValue());
    }

    public void load()
    {
        //Code for load character state here
    }

    public void save()
    {
        //Code for save character state here
    }
}
