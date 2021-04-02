package br.com.kanasha.chess.gson.exclusionStrategy

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes

class ExcludeStrategy: ExclusionStrategy {
    override fun shouldSkipField(f: FieldAttributes?): Boolean {
        return f?.getAnnotation(Exclude::class.java) != null
    }

    override fun shouldSkipClass(clazz: Class<*>?): Boolean {
        return false
    }
}

annotation class Exclude()