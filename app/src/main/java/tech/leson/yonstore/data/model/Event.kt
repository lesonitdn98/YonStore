package tech.leson.yonstore.data.model

class Event(
    var title: String,
    var startTime: Long,
    var endTime: Long,
    var description: String,
    var image: String,
    var discount: Double
) {
    constructor():this("", 0L, 0L, "", "", 0.0)
}
