using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class UpgradeController : MonoBehaviour
{

	public float bloodCurrent;
	public float dameCurrent;
	public float armorCurrent;
	public float coinCurrent;

	[SerializeField]
	TextMeshProUGUI bloodCurrentText;
	[SerializeField]
	TextMeshProUGUI dameCurrentText;
	[SerializeField]
	TextMeshProUGUI armorCurrentText;

	[SerializeField]
	TextMeshProUGUI bloodUpText;
	[SerializeField]
	TextMeshProUGUI dameUpText;
	[SerializeField]
	TextMeshProUGUI armorUpText;
	[SerializeField]
	TextMeshProUGUI coinText;

	const string coinPrefix = "x: ";
	const string upPrefix = "+";

	// Start is called before the first frame update
	void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }


	//28 HE153624 Le Anh Quan - đọc file 
	public void Read()
	{

		string readFilePath = Application.persistentDataPath + "/DataPlayer.txt"; // Đường dẫn của tệp tin cần đọc
		string[] fileContent = File.ReadAllLines(readFilePath); // Đọc nội dung của tệp tin

		// đọc từng row
		for (int i = 0; i < fileContent.Length; i++)
		{

			string[] data = fileContent[i].Split(":");
			bloodCurrent = float.Parse(data[0]);
			dameCurrent = float.Parse(data[1]);
			armorCurrent = float.Parse(data[2]);
			coinCurrent = float.Parse(data[3]);
		}
		//in ra máu, dame và armor đã được lưu vào
		bloodCurrentText.text = bloodCurrent.ToString();
		dameCurrentText.text = dameCurrent.ToString();
		armorCurrentText.text = armorCurrent.ToString();

		//tăng 15% sau mỗi round
		bloodUpText.text = upPrefix + ((int)Math.Round(bloodCurrent * 0.15)).ToString();
		dameUpText.text = upPrefix + ((int)Math.Round(dameCurrent * 0.15)).ToString();
		armorUpText.text = upPrefix + ((int)Math.Round(armorCurrent * 0.15)).ToString();

		coinText.text = coinPrefix + coinCurrent.ToString();

	}


	public void UpgradeBlood(Button button)
	{
		string loadFilePath = Application.persistentDataPath + "/DataPlayer.txt"; // Đường dẫn của tệp tin cần đọc
		File.WriteAllText(loadFilePath, string.Empty);
		// nếu coin >= 100 mới cho mua thêm máu
		if (coinCurrent >= 100)
		{
			bloodCurrent += bloodCurrent * 0.15f;
			coinCurrent -= 100;
		}
		using (StreamWriter writer = new StreamWriter(loadFilePath, true))
		{
			writer.WriteLine(((int)Math.Round(bloodCurrent)).ToString() + ":" + ((int)Math.Round(dameCurrent)).ToString() + ":" + ((int)Math.Round(armorCurrent)).ToString() + ":" + coinCurrent.ToString());
		}
		Read();
	}
	public void UpgradeDame(Button button)
	{
		string loadFilePath = Application.persistentDataPath + "/DataPlayer.txt"; // Đường dẫn của tệp tin cần đọc
		File.WriteAllText(loadFilePath, string.Empty);
		if (coinCurrent >= 100)
		{
			dameCurrent += dameCurrent * 0.15f;
			coinCurrent -= 100;
		}
		using (StreamWriter writer = new StreamWriter(loadFilePath, true))
		{
			writer.WriteLine(((int)Math.Round(bloodCurrent)).ToString() + ":" + ((int)Math.Round(dameCurrent)).ToString() + ":" + ((int)Math.Round(armorCurrent)).ToString() + ":" + coinCurrent.ToString());

		}
		Read();

	}
	public void UpgradeArmor(Button button)
	{
		string loadFilePath = Application.persistentDataPath + "/DataPlayer.txt"; // Đường dẫn của tệp tin cần đọc
		File.WriteAllText(loadFilePath, string.Empty);
		if (coinCurrent >= 100)
		{
			armorCurrent += armorCurrent * 0.15f;
			coinCurrent -= 100;
		}
		using (StreamWriter writer = new StreamWriter(loadFilePath, true))
		{
			writer.WriteLine(((int)Math.Round(bloodCurrent)).ToString() + ":" + ((int)Math.Round(dameCurrent)).ToString() + ":" + ((int)Math.Round(armorCurrent)).ToString() + ":" + coinCurrent.ToString());

		}
		Read();
	}
}
