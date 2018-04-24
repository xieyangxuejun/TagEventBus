package com.foretree.tageventbus

import com.foretree.tageventbus.bus.Subscribe
import java.lang.reflect.Method

/**
 * 三张表:
 * Created by silen on 24/04/2018.
 */
object TagBus {
    private val METHOD_CACHE = hashMapOf<Class<*>, MutableList<SubscriberMethod>>()
    private val SUBSCRIBES = hashMapOf<String, MutableList<Subscription>>()
    private val REGISTERS = hashMapOf<Class<*>, MutableList<String>>()

    fun register(obj: Any) {
        obj::class.java.let {
            var subscriberMethods = METHOD_CACHE[it]
            if (subscriberMethods == null) {
                subscriberMethods = mutableListOf<SubscriberMethod>()
                // get method annotation params
                it.methods.forEach { method ->
                    method.isAccessible = true
                    val parameterTypes = method.parameterTypes
                    it.getAnnotation(Subscribe::class.java).apply {
                        this ?: return@apply
                        this.values.forEach {
                            SubscriberMethod(it, parameterTypes, method)
                            subscriberMethods
                        }
                    }
                }
            }
            METHOD_CACHE[it] = subscriberMethods
            subscriberMethods
        }
    }
}

data class SubscriberMethod(var string: String, var parameterType: Array<Class<*>>, var method: Method)

data class Subscription(var subscriber: Any, var subscriberMethod: SubscriberMethod)