@file:Suppress("ArrayInDataClass")

package com.gwiazdowski.network.dto


data class MapsAutocompleteResponseDto(
    val predictions: Array<Prediction>,
    val status: String,
)

data class Prediction(
    val description: String,
    val matched_substrings: Array<MatchedSubstring>,
    val place_id: String,
    val reference: String,
    val structured_formatting: StructuredFormatting,
    val terms: Array<Term>,
    val types: Array<String>,
)

data class MatchedSubstring(
    val length: Int,
    val offset: Int,
)

data class Term(
    val offset: Int,
    val value: String,
)

data class StructuredFormatting(
    val main_text: String,
    val main_text_matched_substrings: Array<MatchedSubstring>,
    val secondary_text: String,
)