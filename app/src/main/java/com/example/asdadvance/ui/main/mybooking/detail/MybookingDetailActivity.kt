package com.example.asdadvance.ui.main.mybooking.detail

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingActivity
import com.bagicode.bagicodebaseutils.utils.loadRoundedImage
import com.example.asdadvance.databinding.ActivityMybookingDetailBinding
import com.example.asdadvance.model.response.MyBookingResponse
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.Writer
import com.google.zxing.common.BitMatrix
import com.google.zxing.oned.Code128Writer
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.util.*

class MybookingDetailActivity : BaseBindingActivity() {
    lateinit var binding: ActivityMybookingDetailBinding

    override fun getActivityBinding(): ViewBinding {
        binding = ActivityMybookingDetailBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        intent.extras?.let {
            var dataIntent = it.getParcelable<MyBookingResponse>("data")
            initView(dataIntent)
        }
    }

    private fun initView(data: MyBookingResponse?) {
        binding.tvCodeFrom.text = data?.codeTerminalDari
        binding.tvCodeTo.text = data?.codeTerminalTujuan
        binding.tvLocationFrom.text = data?.terminalDari
        binding.tvLocationTo.text = data?.terminalTujuan

        var showName=""
        var showKursi=""

        for (i in data?.penumpang!!.indices) {
            showName = "${data?.penumpang?.get(i)?.nama},"
            showKursi = "${data?.penumpang?.get(i)?.kursi},"
        }
        binding.tvName.text = showName.substring(0, showName.length-1)
        binding.tvKursi.text = showKursi.substring(0, showKursi.length-1)

        binding.tvDate.text = data?.dateKeberangkatan
        binding.tvTime.text = data?.jamKeberangkatan

        binding.ivLogo.loadRoundedImage(data?.busLogo, 4)
        binding.tvTrans.text = data?.pembayaran

        binding.tvPlat.text = data?.busPlat
        binding.tvBooking.text = data?.idTiket

        var barcodeDummy = generateBarcode(data.idTiket!!)
        binding.ivBarcode.setImageBitmap(barcodeDummy)

    }

    private fun generateBarcode(valueParms : String) : Bitmap {
        val hintMap: Hashtable<EncodeHintType, ErrorCorrectionLevel> =
            Hashtable<EncodeHintType, ErrorCorrectionLevel>()
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L)
        val codeWriter: Writer
        codeWriter = Code128Writer()
        val byteMatrix: BitMatrix =
            codeWriter.encode(valueParms, BarcodeFormat.CODE_128, 400, 200, hintMap)
        val width = byteMatrix.width
        val height = byteMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        for (i in 0 until width) {
            for (j in 0 until height) {
                bitmap.setPixel(i, j, if (byteMatrix[i, j]) Color.BLACK else Color.WHITE)
            }
        }
        return bitmap
    }

}