package net.novatech.jbserver.event;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface EventHandler {
	
	EventPriority priority() default EventPriority.NORMAL;
	boolean ignoreCancelled() default true;
	
}