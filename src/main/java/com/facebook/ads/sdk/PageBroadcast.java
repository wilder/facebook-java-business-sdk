/**
 * Copyright (c) 2015-present, Facebook, Inc. All rights reserved.
 *
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to
 * use, copy, modify, and distribute this software in source code or binary
 * form for use in connection with the web services and APIs provided by
 * Facebook.
 *
 * As with any software that integrates with the Facebook platform, your use
 * of this software is subject to the Facebook Developer Principles and
 * Policies [http://developers.facebook.com/policy/]. This copyright notice
 * shall be included in all copies or substantial portions of the software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 */

package com.facebook.ads.sdk;

import java.io.File;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.facebook.ads.sdk.APIException.MalformedResponseException;

/**
 * This class is auto-generated.
 *
 * For any issues or feature requests related to this class, please let us know
 * on github and we'll fix in our codegen framework. We'll not be able to accept
 * pull request for this class.
 *
 */
public class PageBroadcast extends APINode {
  @SerializedName("id")
  private String mId = null;
  @SerializedName("scheduled_time")
  private String mScheduledTime = null;
  @SerializedName("status")
  private String mStatus = null;
  protected static Gson gson = null;

  PageBroadcast() {
  }

  public PageBroadcast(Long id, APIContext context) {
    this(id.toString(), context);
  }

  public PageBroadcast(String id, APIContext context) {
    this.mId = id;

    this.context = context;
  }

  public PageBroadcast fetch() throws APIException{
    PageBroadcast newInstance = fetchById(this.getPrefixedId().toString(), this.context);
    this.copyFrom(newInstance);
    return this;
  }

  public static PageBroadcast fetchById(Long id, APIContext context) throws APIException {
    return fetchById(id.toString(), context);
  }

  public static ListenableFuture<PageBroadcast> fetchByIdAsync(Long id, APIContext context) throws APIException {
    return fetchByIdAsync(id.toString(), context);
  }

  public static PageBroadcast fetchById(String id, APIContext context) throws APIException {
    return
      new APIRequestGet(id, context)
      .requestAllFields()
      .execute();
  }

  public static ListenableFuture<PageBroadcast> fetchByIdAsync(String id, APIContext context) throws APIException {
    return
      new APIRequestGet(id, context)
      .requestAllFields()
      .executeAsync();
  }

  public static APINodeList<PageBroadcast> fetchByIds(List<String> ids, List<String> fields, APIContext context) throws APIException {
    return (APINodeList<PageBroadcast>)(
      new APIRequest<PageBroadcast>(context, "", "/", "GET", PageBroadcast.getParser())
        .setParam("ids", APIRequest.joinStringList(ids))
        .requestFields(fields)
        .execute()
    );
  }

  public static ListenableFuture<APINodeList<PageBroadcast>> fetchByIdsAsync(List<String> ids, List<String> fields, APIContext context) throws APIException {
    return
      new APIRequest(context, "", "/", "GET", PageBroadcast.getParser())
        .setParam("ids", APIRequest.joinStringList(ids))
        .requestFields(fields)
        .executeAsyncBase();
  }

  private String getPrefixedId() {
    return getId();
  }

  public String getId() {
    return getFieldId().toString();
  }
  public static PageBroadcast loadJSON(String json, APIContext context, String header) {
    PageBroadcast pageBroadcast = getGson().fromJson(json, PageBroadcast.class);
    if (context.isDebug()) {
      JsonParser parser = new JsonParser();
      JsonElement o1 = parser.parse(json);
      JsonElement o2 = parser.parse(pageBroadcast.toString());
      if (o1.getAsJsonObject().get("__fb_trace_id__") != null) {
        o2.getAsJsonObject().add("__fb_trace_id__", o1.getAsJsonObject().get("__fb_trace_id__"));
      }
      if (!o1.equals(o2)) {
        context.log("[Warning] When parsing response, object is not consistent with JSON:");
        context.log("[JSON]" + o1);
        context.log("[Object]" + o2);
      };
    }
    pageBroadcast.context = context;
    pageBroadcast.rawValue = json;
    pageBroadcast.header = header;
    return pageBroadcast;
  }

  public static APINodeList<PageBroadcast> parseResponse(String json, APIContext context, APIRequest request, String header) throws MalformedResponseException {
    APINodeList<PageBroadcast> pageBroadcasts = new APINodeList<PageBroadcast>(request, json, header);
    JsonArray arr;
    JsonObject obj;
    JsonParser parser = new JsonParser();
    Exception exception = null;
    try{
      JsonElement result = parser.parse(json);
      if (result.isJsonArray()) {
        // First, check if it's a pure JSON Array
        arr = result.getAsJsonArray();
        for (int i = 0; i < arr.size(); i++) {
          pageBroadcasts.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
        };
        return pageBroadcasts;
      } else if (result.isJsonObject()) {
        obj = result.getAsJsonObject();
        if (obj.has("data")) {
          if (obj.has("paging")) {
            JsonObject paging = obj.get("paging").getAsJsonObject();
            if (paging.has("cursors")) {
                JsonObject cursors = paging.get("cursors").getAsJsonObject();
                String before = cursors.has("before") ? cursors.get("before").getAsString() : null;
                String after = cursors.has("after") ? cursors.get("after").getAsString() : null;
                pageBroadcasts.setCursors(before, after);
            }
            String previous = paging.has("previous") ? paging.get("previous").getAsString() : null;
            String next = paging.has("next") ? paging.get("next").getAsString() : null;
            pageBroadcasts.setPaging(previous, next);
            if (context.hasAppSecret()) {
              pageBroadcasts.setAppSecret(context.getAppSecretProof());
            }
          }
          if (obj.get("data").isJsonArray()) {
            // Second, check if it's a JSON array with "data"
            arr = obj.get("data").getAsJsonArray();
            for (int i = 0; i < arr.size(); i++) {
              pageBroadcasts.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
            };
          } else if (obj.get("data").isJsonObject()) {
            // Third, check if it's a JSON object with "data"
            obj = obj.get("data").getAsJsonObject();
            boolean isRedownload = false;
            for (String s : new String[]{"campaigns", "adsets", "ads"}) {
              if (obj.has(s)) {
                isRedownload = true;
                obj = obj.getAsJsonObject(s);
                for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                  pageBroadcasts.add(loadJSON(entry.getValue().toString(), context, header));
                }
                break;
              }
            }
            if (!isRedownload) {
              pageBroadcasts.add(loadJSON(obj.toString(), context, header));
            }
          }
          return pageBroadcasts;
        } else if (obj.has("images")) {
          // Fourth, check if it's a map of image objects
          obj = obj.get("images").getAsJsonObject();
          for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
              pageBroadcasts.add(loadJSON(entry.getValue().toString(), context, header));
          }
          return pageBroadcasts;
        } else {
          // Fifth, check if it's an array of objects indexed by id
          boolean isIdIndexedArray = true;
          for (Map.Entry entry : obj.entrySet()) {
            String key = (String) entry.getKey();
            if (key.equals("__fb_trace_id__")) {
              continue;
            }
            JsonElement value = (JsonElement) entry.getValue();
            if (
              value != null &&
              value.isJsonObject() &&
              value.getAsJsonObject().has("id") &&
              value.getAsJsonObject().get("id") != null &&
              value.getAsJsonObject().get("id").getAsString().equals(key)
            ) {
              pageBroadcasts.add(loadJSON(value.toString(), context, header));
            } else {
              isIdIndexedArray = false;
              break;
            }
          }
          if (isIdIndexedArray) {
            return pageBroadcasts;
          }

          // Sixth, check if it's pure JsonObject
          pageBroadcasts.clear();
          pageBroadcasts.add(loadJSON(json, context, header));
          return pageBroadcasts;
        }
      }
    } catch (Exception e) {
      exception = e;
    }
    throw new MalformedResponseException(
      "Invalid response string: " + json,
      exception
    );
  }

  @Override
  public APIContext getContext() {
    return context;
  }

  @Override
  public void setContext(APIContext context) {
    this.context = context;
  }

  @Override
  public String toString() {
    return getGson().toJson(this);
  }

  public APIRequestGetInsights getInsights() {
    return new APIRequestGetInsights(this.getPrefixedId().toString(), context);
  }

  public APIRequestGet get() {
    return new APIRequestGet(this.getPrefixedId().toString(), context);
  }

  public APIRequestUpdate update() {
    return new APIRequestUpdate(this.getPrefixedId().toString(), context);
  }


  public String getFieldId() {
    return mId;
  }

  public String getFieldScheduledTime() {
    return mScheduledTime;
  }

  public String getFieldStatus() {
    return mStatus;
  }



  public static class APIRequestGetInsights extends APIRequest<InsightsResult> {

    APINodeList<InsightsResult> lastResponse = null;
    @Override
    public APINodeList<InsightsResult> getLastResponse() {
      return lastResponse;
    }
    public static final String[] PARAMS = {
      "metric",
    };

    public static final String[] FIELDS = {
      "description",
      "description_from_api_doc",
      "id",
      "name",
      "period",
      "title",
      "values",
    };

    @Override
    public APINodeList<InsightsResult> parseResponse(String response, String header) throws APIException {
      return InsightsResult.parseResponse(response, getContext(), this, header);
    }

    @Override
    public APINodeList<InsightsResult> execute() throws APIException {
      return execute(new HashMap<String, Object>());
    }

    @Override
    public APINodeList<InsightsResult> execute(Map<String, Object> extraParams) throws APIException {
      ResponseWrapper rw = executeInternal(extraParams);
      lastResponse = parseResponse(rw.getBody(),rw.getHeader());
      return lastResponse;
    }

    public ListenableFuture<APINodeList<InsightsResult>> executeAsync() throws APIException {
      return executeAsync(new HashMap<String, Object>());
    };

    public ListenableFuture<APINodeList<InsightsResult>> executeAsync(Map<String, Object> extraParams) throws APIException {
      return Futures.transform(
        executeAsyncInternal(extraParams),
        new Function<ResponseWrapper, APINodeList<InsightsResult>>() {
           public APINodeList<InsightsResult> apply(ResponseWrapper result) {
             try {
               return APIRequestGetInsights.this.parseResponse(result.getBody(), result.getHeader());
             } catch (Exception e) {
               throw new RuntimeException(e);
             }
           }
         }
      );
    };

    public APIRequestGetInsights(String nodeId, APIContext context) {
      super(context, nodeId, "/insights", "GET", Arrays.asList(PARAMS));
    }

    @Override
    public APIRequestGetInsights setParam(String param, Object value) {
      setParamInternal(param, value);
      return this;
    }

    @Override
    public APIRequestGetInsights setParams(Map<String, Object> params) {
      setParamsInternal(params);
      return this;
    }


    public APIRequestGetInsights setMetric (List<InsightsResult.EnumMetric> metric) {
      this.setParam("metric", metric);
      return this;
    }
    public APIRequestGetInsights setMetric (String metric) {
      this.setParam("metric", metric);
      return this;
    }

    public APIRequestGetInsights requestAllFields () {
      return this.requestAllFields(true);
    }

    public APIRequestGetInsights requestAllFields (boolean value) {
      for (String field : FIELDS) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestGetInsights requestFields (List<String> fields) {
      return this.requestFields(fields, true);
    }

    @Override
    public APIRequestGetInsights requestFields (List<String> fields, boolean value) {
      for (String field : fields) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestGetInsights requestField (String field) {
      this.requestField(field, true);
      return this;
    }

    @Override
    public APIRequestGetInsights requestField (String field, boolean value) {
      this.requestFieldInternal(field, value);
      return this;
    }

    public APIRequestGetInsights requestDescriptionField () {
      return this.requestDescriptionField(true);
    }
    public APIRequestGetInsights requestDescriptionField (boolean value) {
      this.requestField("description", value);
      return this;
    }
    public APIRequestGetInsights requestDescriptionFromApiDocField () {
      return this.requestDescriptionFromApiDocField(true);
    }
    public APIRequestGetInsights requestDescriptionFromApiDocField (boolean value) {
      this.requestField("description_from_api_doc", value);
      return this;
    }
    public APIRequestGetInsights requestIdField () {
      return this.requestIdField(true);
    }
    public APIRequestGetInsights requestIdField (boolean value) {
      this.requestField("id", value);
      return this;
    }
    public APIRequestGetInsights requestNameField () {
      return this.requestNameField(true);
    }
    public APIRequestGetInsights requestNameField (boolean value) {
      this.requestField("name", value);
      return this;
    }
    public APIRequestGetInsights requestPeriodField () {
      return this.requestPeriodField(true);
    }
    public APIRequestGetInsights requestPeriodField (boolean value) {
      this.requestField("period", value);
      return this;
    }
    public APIRequestGetInsights requestTitleField () {
      return this.requestTitleField(true);
    }
    public APIRequestGetInsights requestTitleField (boolean value) {
      this.requestField("title", value);
      return this;
    }
    public APIRequestGetInsights requestValuesField () {
      return this.requestValuesField(true);
    }
    public APIRequestGetInsights requestValuesField (boolean value) {
      this.requestField("values", value);
      return this;
    }
  }

  public static class APIRequestGet extends APIRequest<PageBroadcast> {

    PageBroadcast lastResponse = null;
    @Override
    public PageBroadcast getLastResponse() {
      return lastResponse;
    }
    public static final String[] PARAMS = {
    };

    public static final String[] FIELDS = {
      "id",
      "scheduled_time",
      "status",
    };

    @Override
    public PageBroadcast parseResponse(String response, String header) throws APIException {
      return PageBroadcast.parseResponse(response, getContext(), this, header).head();
    }

    @Override
    public PageBroadcast execute() throws APIException {
      return execute(new HashMap<String, Object>());
    }

    @Override
    public PageBroadcast execute(Map<String, Object> extraParams) throws APIException {
      ResponseWrapper rw = executeInternal(extraParams);
      lastResponse = parseResponse(rw.getBody(), rw.getHeader());
      return lastResponse;
    }

    public ListenableFuture<PageBroadcast> executeAsync() throws APIException {
      return executeAsync(new HashMap<String, Object>());
    };

    public ListenableFuture<PageBroadcast> executeAsync(Map<String, Object> extraParams) throws APIException {
      return Futures.transform(
        executeAsyncInternal(extraParams),
        new Function<ResponseWrapper, PageBroadcast>() {
           public PageBroadcast apply(ResponseWrapper result) {
             try {
               return APIRequestGet.this.parseResponse(result.getBody(), result.getHeader());
             } catch (Exception e) {
               throw new RuntimeException(e);
             }
           }
         }
      );
    };

    public APIRequestGet(String nodeId, APIContext context) {
      super(context, nodeId, "/", "GET", Arrays.asList(PARAMS));
    }

    @Override
    public APIRequestGet setParam(String param, Object value) {
      setParamInternal(param, value);
      return this;
    }

    @Override
    public APIRequestGet setParams(Map<String, Object> params) {
      setParamsInternal(params);
      return this;
    }


    public APIRequestGet requestAllFields () {
      return this.requestAllFields(true);
    }

    public APIRequestGet requestAllFields (boolean value) {
      for (String field : FIELDS) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestGet requestFields (List<String> fields) {
      return this.requestFields(fields, true);
    }

    @Override
    public APIRequestGet requestFields (List<String> fields, boolean value) {
      for (String field : fields) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestGet requestField (String field) {
      this.requestField(field, true);
      return this;
    }

    @Override
    public APIRequestGet requestField (String field, boolean value) {
      this.requestFieldInternal(field, value);
      return this;
    }

    public APIRequestGet requestIdField () {
      return this.requestIdField(true);
    }
    public APIRequestGet requestIdField (boolean value) {
      this.requestField("id", value);
      return this;
    }
    public APIRequestGet requestScheduledTimeField () {
      return this.requestScheduledTimeField(true);
    }
    public APIRequestGet requestScheduledTimeField (boolean value) {
      this.requestField("scheduled_time", value);
      return this;
    }
    public APIRequestGet requestStatusField () {
      return this.requestStatusField(true);
    }
    public APIRequestGet requestStatusField (boolean value) {
      this.requestField("status", value);
      return this;
    }
  }

  public static class APIRequestUpdate extends APIRequest<PageBroadcast> {

    PageBroadcast lastResponse = null;
    @Override
    public PageBroadcast getLastResponse() {
      return lastResponse;
    }
    public static final String[] PARAMS = {
      "operation",
    };

    public static final String[] FIELDS = {
    };

    @Override
    public PageBroadcast parseResponse(String response, String header) throws APIException {
      return PageBroadcast.parseResponse(response, getContext(), this, header).head();
    }

    @Override
    public PageBroadcast execute() throws APIException {
      return execute(new HashMap<String, Object>());
    }

    @Override
    public PageBroadcast execute(Map<String, Object> extraParams) throws APIException {
      ResponseWrapper rw = executeInternal(extraParams);
      lastResponse = parseResponse(rw.getBody(), rw.getHeader());
      return lastResponse;
    }

    public ListenableFuture<PageBroadcast> executeAsync() throws APIException {
      return executeAsync(new HashMap<String, Object>());
    };

    public ListenableFuture<PageBroadcast> executeAsync(Map<String, Object> extraParams) throws APIException {
      return Futures.transform(
        executeAsyncInternal(extraParams),
        new Function<ResponseWrapper, PageBroadcast>() {
           public PageBroadcast apply(ResponseWrapper result) {
             try {
               return APIRequestUpdate.this.parseResponse(result.getBody(), result.getHeader());
             } catch (Exception e) {
               throw new RuntimeException(e);
             }
           }
         }
      );
    };

    public APIRequestUpdate(String nodeId, APIContext context) {
      super(context, nodeId, "/", "POST", Arrays.asList(PARAMS));
    }

    @Override
    public APIRequestUpdate setParam(String param, Object value) {
      setParamInternal(param, value);
      return this;
    }

    @Override
    public APIRequestUpdate setParams(Map<String, Object> params) {
      setParamsInternal(params);
      return this;
    }


    public APIRequestUpdate setOperation (PageBroadcast.EnumOperation operation) {
      this.setParam("operation", operation);
      return this;
    }
    public APIRequestUpdate setOperation (String operation) {
      this.setParam("operation", operation);
      return this;
    }

    public APIRequestUpdate requestAllFields () {
      return this.requestAllFields(true);
    }

    public APIRequestUpdate requestAllFields (boolean value) {
      for (String field : FIELDS) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestUpdate requestFields (List<String> fields) {
      return this.requestFields(fields, true);
    }

    @Override
    public APIRequestUpdate requestFields (List<String> fields, boolean value) {
      for (String field : fields) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestUpdate requestField (String field) {
      this.requestField(field, true);
      return this;
    }

    @Override
    public APIRequestUpdate requestField (String field, boolean value) {
      this.requestFieldInternal(field, value);
      return this;
    }

  }

  public static enum EnumOperation {
      @SerializedName("CANCEL")
      VALUE_CANCEL("CANCEL"),
      NULL(null);

      private String value;

      private EnumOperation(String value) {
        this.value = value;
      }

      @Override
      public String toString() {
        return value;
      }
  }


  synchronized /*package*/ static Gson getGson() {
    if (gson != null) {
      return gson;
    } else {
      gson = new GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.STATIC)
        .excludeFieldsWithModifiers(Modifier.PROTECTED)
        .disableHtmlEscaping()
        .create();
    }
    return gson;
  }

  public PageBroadcast copyFrom(PageBroadcast instance) {
    this.mId = instance.mId;
    this.mScheduledTime = instance.mScheduledTime;
    this.mStatus = instance.mStatus;
    this.context = instance.context;
    this.rawValue = instance.rawValue;
    return this;
  }

  public static APIRequest.ResponseParser<PageBroadcast> getParser() {
    return new APIRequest.ResponseParser<PageBroadcast>() {
      public APINodeList<PageBroadcast> parseResponse(String response, APIContext context, APIRequest<PageBroadcast> request, String header) throws MalformedResponseException {
        return PageBroadcast.parseResponse(response, context, request, header);
      }
    };
  }
}
