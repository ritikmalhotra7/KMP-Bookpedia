package com.sample.bookpedia.feat_book.data.serializers

import com.sample.bookpedia.core.utils.printWith
import com.sample.bookpedia.feat_book.data.dto.details.BookWorkDto
import com.sample.bookpedia.feat_book.data.dto.details.DescriptionDto
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

object BookWorkSerializer: KSerializer<BookWorkDto> {
    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor(
            serialName = BookWorkDto::class.simpleName!!
        ){
            element<String?>(BookWorkDto::description.name)
        }

    override fun serialize(
        encoder: Encoder,
        value: BookWorkDto
    ) = encoder.encodeStructure(descriptor){
        value.description?.let{
            encodeStringElement(descriptor,0,it)
        }
    }

    override fun deserialize(decoder: Decoder): BookWorkDto = decoder.decodeStructure(descriptor) {
        var description :String? = null
        while(true){
            when(val index = decodeElementIndex(descriptor)){
                0->{
                    val jsonDecoder = decoder as JsonDecoder?:throw SerializationException("This decoder only works with JSON!")
                    val elements = jsonDecoder.decodeJsonElement()
                    description = if(elements is JsonObject){
                        decoder.json.decodeFromJsonElement<DescriptionDto>(element = elements, deserializer = DescriptionDto.serializer()).value
                    }else if(elements is JsonPrimitive && elements.isString){
                        elements.toString()
                    }else null
                    description?.printWith("description")
                }
                CompositeDecoder.DECODE_DONE -> break
                else -> throw SerializationException("Unexpected index $index")
            }
        }
        return@decodeStructure BookWorkDto(description = description)
    }
}