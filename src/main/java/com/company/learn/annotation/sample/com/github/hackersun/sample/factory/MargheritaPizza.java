/*
 * Copyright (C) 2015 Hannes Dorfmann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.company.learn.annotation.sample.com.github.hackersun.sample.factory;


import com.company.learn.annotation.annotation.com.github.hackersun.annotation.Factory;

/**
 * @author Hannes Dorfmann
 */

@Factory(
    id = "Magherita",
    type = Meal.class
)
public class MargheritaPizza implements Meal {

  public float getPrice() {
    return 6f;
  }

  public String getName() {
    return "Margherita Pizza";
  }
}
